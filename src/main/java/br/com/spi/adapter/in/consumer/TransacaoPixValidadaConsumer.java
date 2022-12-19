package br.com.spi.adapter.in.consumer;

import br.com.spi.infrastructure.dto.transacao.TransacaoValidadaRequest;
import br.com.spi.port.out.ValidacaoTransacaoInputPort;
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
public class TransacaoPixValidadaConsumer {

    private final ValidacaoTransacaoInputPort inputPort;

    @KafkaListener(id="group-validacao-bacen1", topics = "${topic.name.recebedor.retorno.success}")
    public void listenSuccess(ConsumerRecord<String, String> mensagemKafka, Acknowledgment ack) {
        try {
            var request = processConsumerRecord(mensagemKafka);
            inputPort.retornarSucesso(request);
        } catch (JsonProcessingException ex) {
            log.error("#### Error consuming message -> {},{}", ex.getMessage(), ex.getStackTrace());
        } finally {
            ack.acknowledge();
        }
    }

    @KafkaListener(id="group-validacao-bacen2", topics = "${topic.name.recebedor.retorno.fail}")
    public void listenFail(ConsumerRecord<String, String> mensagemKafka, Acknowledgment ack) {
        try {
            var request = processConsumerRecord(mensagemKafka);
            inputPort.retornarFalha(request);
        } catch (JsonProcessingException ex) {
            log.error("#### Error consuming message -> {},{}", ex.getMessage(), ex.getStackTrace());
        } finally {
            ack.acknowledge();
        }
    }

    private TransacaoValidadaRequest processConsumerRecord(ConsumerRecord<String, String> mensagemKafka) throws JsonProcessingException{
        log.info("#### Message consumed -> {}, topic -> {}", mensagemKafka.value(), mensagemKafka.topic());
        var request = new ObjectMapper().readValue(mensagemKafka.value(), TransacaoValidadaRequest.class);
        return request;
    }
}
