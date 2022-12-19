package br.com.spi.adapter.in.consumer;


import br.com.spi.infrastructure.dto.ChavePixRequest;
import br.com.spi.port.in.ChavePixRegistrationInputPort;
import com.fasterxml.jackson.core.JsonProcessingException;
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
    private final ChavePixRegistrationInputPort inputPort;

    @KafkaListener(id="${spring.kafka.consumer.group-id}", topics = "${topic.name.envio}")
    public void listen(ConsumerRecord<String, String> mensagemKafka, Acknowledgment ack) {
        try {
            processConsumerRecord(mensagemKafka);
        } catch (JsonProcessingException ex) {
            log.error("# Error consuming Chave Pix registration message -> {}, {}",
                    mensagemKafka.key(), ex.getMessage());
        } finally {
            ack.acknowledge();
        }
    }

    private void processConsumerRecord(ConsumerRecord<String, String> mensagemKafka) throws JsonProcessingException{
        log.info("# Chave Pix registration message consumed -> Transaction Id: {}, Timestamp: {}",
                mensagemKafka.key(), mensagemKafka.timestamp());
        var request = new ObjectMapper().readValue(mensagemKafka.value(), ChavePixRequest.class);
        inputPort.registerChavePix(request);
    }
}