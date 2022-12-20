package br.com.spi.adapter.out.producer;

import br.com.spi.infrastructure.dto.chave.ChavePixResponse;
import br.com.spi.port.out.ChavePixRegistrationNotify;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ChavePixRegistrationProducer implements ChavePixRegistrationNotify{
    @Value("${topic.name.pix.key.send.success}")
    private String topicSuccess;
    @Value("${topic.name.pix.key.send.failure}")
    private String topicFailure;
    private final KafkaTemplate<String, ChavePixResponse> kafkaTemplate;

    @Override
    public void notifySuccessfulRegistration(ChavePixResponse response){
        sendChavePixResponseToTopic(response, topicSuccess);
        log.info("#### Chave pix cadastrada - mensagem: {}", response);
    }

    @Override
    public void notifyRegistrationFailure(ChavePixResponse response){
        sendChavePixResponseToTopic(response, topicFailure);
        log.info("#### Erro cadastro chave pix - mensagem: {}", response);
    }

    private void sendChavePixResponseToTopic(ChavePixResponse response, String topic){
        kafkaTemplate.send(topic, response.getTransactionId(), response);
        kafkaTemplate.flush();
    }
}


