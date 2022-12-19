package br.com.spi.adapter.out.producer;

import br.com.spi.infrastructure.dto.transacao.TransacaoValidadaResponse;
import br.com.spi.port.out.ValidacaoTransacaoOutputPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ValidacaoTransacaoPixProducer implements ValidacaoTransacaoOutputPort {

    @Value("${topic.name.resultado.success}")
    private String topicTransacaoSuccess;
    @Value("${topic.name.resultado.fail}")
    private String topicTransacaoFailure;
    @Value("${topic.name.pagador.retorno.success}")
    private String topicRetornoSuccess;
    @Value("${topic.name.pagador.retorno.fail}")
    private String topicRetornoFailure;

    private final KafkaTemplate<String, TransacaoValidadaResponse> kafkaTemplate;

    public ValidacaoTransacaoPixProducer(KafkaTemplate<String, TransacaoValidadaResponse> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void notificaSucesso(TransacaoValidadaResponse response) {
        sendTransacaoPixResponseToTopic(response, topicRetornoSuccess);
        sendTransacaoPixResponseToTopic(response, topicTransacaoSuccess);
        log.info("#### Retorno Transacao Pix Sucesso- mensagem: {}", response);
    }

    @Override
    public void notificaFalha(TransacaoValidadaResponse response) {
        sendTransacaoPixResponseToTopic(response, topicRetornoFailure);
        sendTransacaoPixResponseToTopic(response, topicTransacaoFailure);
        log.info("#### Retorno Transacao Pix Falha - mensagem: {}", response);
    }

    private void sendTransacaoPixResponseToTopic(TransacaoValidadaResponse response, String topic){
        kafkaTemplate.send(topic, response.getTransactionId(), response);
        kafkaTemplate.flush();
    }
}
