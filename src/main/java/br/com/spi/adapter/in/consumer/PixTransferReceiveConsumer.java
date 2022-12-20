package br.com.spi.adapter.in.consumer;

import br.com.spi.exception.TransacaoPixParseException;
import br.com.spi.infrastructure.dto.transacao.PixTransferRequest;
import br.com.spi.port.in.PixTransferReceive;
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
public class PixTransferReceiveConsumer{

    private final PixTransferReceive pixReceive;

    @KafkaListener(id="${spring.kafka.consumer.group-id.transfer.receive}", topics = "${topic.name.pix.transfer.receive}")
    public void listen(ConsumerRecord<String, String> mensagemKafka, Acknowledgment ack) {
        try {
            processConsumerRecord(mensagemKafka);
        } catch (JsonProcessingException ex) {
            log.error("#### Error consuming message -> {},{}", ex.getMessage(), ex.getStackTrace());
            throw new TransacaoPixParseException("Erro ao converter mensagem recebida",ex);

        } finally {
            ack.acknowledge();
        }
    }

    private void processConsumerRecord(ConsumerRecord<String, String> mensagemKafka) throws JsonProcessingException{
        log.info("#### Message consumed -> {}, topic -> {}", mensagemKafka.value(), mensagemKafka.topic());
        var request = new ObjectMapper().readValue(mensagemKafka.value(), PixTransferRequest.class);
        pixReceive.validatePix(request);
    }

}
