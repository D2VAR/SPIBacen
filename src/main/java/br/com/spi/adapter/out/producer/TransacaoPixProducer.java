package br.com.spi.adapter.out.producer;

import br.com.spi.infrastructure.dto.transacao.TransacaoPixResponse;
import br.com.spi.port.out.TransacaoPixOutputPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TransacaoPixProducer implements TransacaoPixOutputPort {

    @Value("${topic.name.recebedor.envio.pix}")
    private String topic;

    private final KafkaTemplate<String, TransacaoPixResponse> kafkaTemplate;

    public TransacaoPixProducer(KafkaTemplate<String, TransacaoPixResponse> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void enviarPix(TransacaoPixResponse response) {
        sendTransacaoPixToTopic(response,topic);
        log.info("#### Transacao Pix enviada - mensagem: {}", response);
    }

    private void sendTransacaoPixToTopic(TransacaoPixResponse response, String topic){
        kafkaTemplate.send(topic, response.getTransactionId(), response);
        kafkaTemplate.flush();
    }
}
