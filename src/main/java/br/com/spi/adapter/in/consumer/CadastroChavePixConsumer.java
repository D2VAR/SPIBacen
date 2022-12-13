package br.com.spi.adapter.in.consumer;


import br.com.spi.domain.dto.ChavePixDTO;
import br.com.spi.infrastructure.validator.ChaveValidator;
import br.com.spi.port.in.CadastroChavePixInputPort;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CadastroChavePixConsumer {

    private final ChaveValidator chaveValidator;
    private final CadastroChavePixInputPort inputPort;

    @KafkaListener(id="${spring.kafka.consumer.group-id}", topics = "${topic.name.envio}")
    public void listen(ConsumerRecord<String, String> mensagemKafka, Acknowledgment ack) {
        try {
            log.info(String.format("#### Mensagem Consumida -> %s, topic -> %s",
                    mensagemKafka.value(), mensagemKafka.topic()));
            ChavePixDTO chavePixDTO = new ObjectMapper().readValue(mensagemKafka.value(), ChavePixDTO.class);
            chaveValidator.validate(chavePixDTO);
            inputPort.cadastrarChave(chavePixDTO);


        } catch (Exception ex) {
            log.error("#### ErroConsumerMensagem -> {},{}", ex.getMessage(), ex.getStackTrace());

        } finally {
            ack.acknowledge();
        }
    }
}