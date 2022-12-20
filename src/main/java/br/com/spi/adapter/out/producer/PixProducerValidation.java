package br.com.spi.adapter.out.producer;

import br.com.spi.infrastructure.dto.transacao.PixTransferResponse;
import br.com.spi.port.out.PixTransferValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PixProducerValidation implements PixTransferValidation{
    @Value("${topic.name.pix.transfer.receiver.validation}")
    private String topic;
    private final KafkaTemplate<String, PixTransferResponse> kafkaTemplate;


    @Override
    public void enviarPix(PixTransferResponse response) {
        var bankTopic = getRightTopic(response.getCodBancoOrigem());
        sendTransacaoPixToTopic(response, bankTopic);
        log.info("#### Transacao Pix enviada - mensagem: {}", response);
    }
    private String getRightTopic(String codigoBancoOrigem){
        if ("341".equals(codigoBancoOrigem))
            return topic.concat("-201");
        else
            return topic.concat("-341");
    }

    private void sendTransacaoPixToTopic(PixTransferResponse response, String topic){
        kafkaTemplate.send(topic, response.getTransactionId(), response);
        kafkaTemplate.flush();
    }
}
