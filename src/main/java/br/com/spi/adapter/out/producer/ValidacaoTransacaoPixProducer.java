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
        setRightSuccessTopics(response.getCodBancoOrigem());
        sendTransacaoPixResponseToTopic(response, topicSenderSuccess);
        sendTransacaoPixResponseToTopic(response, topicReceiverSuccess);
        log.info("#### Retorno Transacao Pix Sucesso- mensagem: {}", response);
    }

    private void setRightSuccessTopics(String codigoBancoOrigem){
        if (ITAU.equals(codigoBancoOrigem)){
            topicReceiverSuccess = updateTopicSufix(topicReceiverSuccess, ADA);
            topicSenderSuccess = updateTopicSufix(topicSenderSuccess, ITAU);
        } else{
            topicReceiverSuccess = updateTopicSufix(topicReceiverSuccess, ITAU);
            topicSenderSuccess = updateTopicSufix(topicSenderSuccess, ADA);
        }
    }

    private String updateTopicSufix(String topic, String codBanco){
        var list = topic.split("-");
        var lastElement = list[list.length - 1];
        var response = "";
        if (lastElement.equals(ITAU)){
            response = topic.replace(ITAU, codBanco);
        } else if (lastElement.equals(ADA)){
            response = topic.replace(ADA, codBanco);
        } else{
            response = topic.concat("-").concat(codBanco);
        }
        return response;
    }

    @Override
    public void notificaFalha(TransacaoValidadaResponse response){
        setRightFailureTopics(response.getCodBancoOrigem());
        sendTransacaoPixResponseToTopic(response, topicSenderFailure);
        sendTransacaoPixResponseToTopic(response, topicReceiverFailure);
        log.info("#### Retorno Transacao Pix Falha - mensagem: {}", response);
    }

    private void setRightFailureTopics(String codigoBancoOrigem){
        if (ITAU.equals(codigoBancoOrigem)){
            topicReceiverFailure = updateTopicSufix(topicReceiverFailure, ADA);
            topicSenderFailure = updateTopicSufix(topicSenderFailure, ITAU);
        } else{
            topicReceiverFailure = updateTopicSufix(topicReceiverFailure, ITAU);
            topicSenderFailure = updateTopicSufix(topicSenderFailure, ADA);
        }
    }

    private void sendTransacaoPixResponseToTopic(TransacaoValidadaResponse response, String topic){
        kafkaTemplate.send(topic, response.getTransactionId(), response);
        kafkaTemplate.flush();
    }
}
