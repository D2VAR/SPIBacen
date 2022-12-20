package br.com.spi.adapter.in.consumer;

import br.com.spi.exception.TransacaoValidadaParseException;
import br.com.spi.infrastructure.dto.transacao.TransacaoValidadaRequest;
import br.com.spi.port.in.ValidacaoTransacaoInputPort;
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
public class PixTransferValidationConsumer{

    private final ValidacaoTransacaoInputPort inputPort;

    @KafkaListener(id="${spring.kafka.consumer.group-id.transfer.success}", topics = "${topic.name.pix.transfer.validation.success}")
    public void listenSuccess(ConsumerRecord<String, String> mensagemKafka, Acknowledgment ack) {
        try {
            var request = processConsumerRecord(mensagemKafka);
            inputPort.retornarValidacao(request);
        } catch (JsonProcessingException ex) {
            log.error("#### Error consuming message -> {},{}", ex.getMessage(), ex.getStackTrace());
            throw new TransacaoValidadaParseException("Erro ao converter mensagem recebida",ex);

        } finally {
            ack.acknowledge();
        }
    }

    @KafkaListener(id="${spring.kafka.consumer.group-id.transfer.failure}", topics = "${topic.name.pix.transfer.validation.failure}")
    public void listenFail(ConsumerRecord<String, String> mensagemKafka, Acknowledgment ack) {
        try {
            var request = processConsumerRecord(mensagemKafka);
            inputPort.retornarValidacao(request);
        } catch (JsonProcessingException ex) {
            log.error("#### Error consuming message -> {},{}", ex.getMessage(), ex.getStackTrace());
            throw new TransacaoValidadaParseException("Erro ao converter mensagem recebida",ex);

        } finally {
            ack.acknowledge();
        }
    }

    private TransacaoValidadaRequest processConsumerRecord(ConsumerRecord<String, String> mensagemKafka) throws JsonProcessingException{
        log.info("#### Message consumed -> {}, topic -> {}", mensagemKafka.value(), mensagemKafka.topic());
        return new ObjectMapper().readValue(mensagemKafka.value(), TransacaoValidadaRequest.class);
    }
}
