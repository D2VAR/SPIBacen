package br.com.spi.adapter.out.producer;

import br.com.spi.infrastructure.dto.transacao.TransacaoValidadaResponse;
import br.com.spi.port.out.PixTransferFinalization;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ValidacaoTransacaoPixProducer implements PixTransferFinalization{

    @Value("${topic.name.pix.transfer.receiver.finalization.success}")
    private String topicReceiverSuccess;
    @Value("${topic.name.pix.transfer.receiver.finalization.success}")
    private String topicReceiverFailure;
    @Value("${topic.name.pix.transfer.sender.finalization.success}")
    private String topicSenderSuccess;
    @Value("${topic.name.pix.transfer.sender.finalization.success}")
    private String topicSenderFailure;

    private static final String ITAU = "341";
    private static final String ADA = "201";

    private final KafkaTemplate<String, TransacaoValidadaResponse> kafkaTemplate;

    @Override
    public void notificaSucesso(TransacaoValidadaResponse response){
        var senderTopic = getRightTopicSender(topicSenderSuccess, response.getCodBancoOrigem());
        var receiverTopic = getRightTopicReceiver(topicReceiverSuccess, response.getCodBancoOrigem());
        sendTransacaoPixResponseToTopic(response, senderTopic);
        sendTransacaoPixResponseToTopic(response, receiverTopic);
        log.info("#### Retorno Transacao Pix Sucesso- mensagem: {}", response);
    }

    private String getRightTopicReceiver(String topic, String codBanco){
        if (ITAU.equals(codBanco))
            return topic.concat("-").concat(ADA);
        return topic.concat("-").concat(ITAU);
    }
    private String getRightTopicSender(String topic, String codBanco){
        return topic.concat("-").concat(codBanco);
    }

    @Override
    public void notificaFalha(TransacaoValidadaResponse response){
        var senderTopic = getRightTopicSender(topicSenderFailure, response.getCodBancoOrigem());
        var receiverTopic = getRightTopicReceiver(topicReceiverFailure, response.getCodBancoOrigem());
        sendTransacaoPixResponseToTopic(response, senderTopic);
        sendTransacaoPixResponseToTopic(response, receiverTopic);
        log.info("#### Retorno Transacao Pix Falha - mensagem: {}", response);
    }


    private void sendTransacaoPixResponseToTopic(TransacaoValidadaResponse response, String topic){
        kafkaTemplate.send(topic, response.getTransactionId(), response);
        kafkaTemplate.flush();
    }
}
