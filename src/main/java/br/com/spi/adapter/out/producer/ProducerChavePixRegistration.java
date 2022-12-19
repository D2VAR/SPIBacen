package br.com.spi.adapter.out.producer;

import br.com.spi.infrastructure.dto.ChavePixResponse;
import br.com.spi.port.out.ChavePixRegistrationOutputPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProducerChavePixRegistration implements ChavePixRegistrationOutputPort{
    @Value("${topic.name.retorno.success}")
    private String topicSuccess;
    @Value("${topic.name.retorno.fail}")
    private String topicFailure;
    private final KafkaTemplate<String, ChavePixResponse> kafkaTemplate;

    public ProducerChavePixRegistration(KafkaTemplate<String, ChavePixResponse> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }
    @Override
    public void notifySuccessfulRegistration(ChavePixResponse response){
        sendChavePixResponseToTopic(response, topicSuccess);
        log.info("## Chave pix registrated! - Transaction Id: {}, Chave Pix: {}",
                response.getTransactionId(), response.getValorChave());
    }

    @Override
    public void notifyRegistrationFailure(ChavePixResponse response){
        sendChavePixResponseToTopic(response, topicFailure);
        log.info("## Error registering Chave Pix - Transaction Id: {}, Chave Pix: {}",
                response.getTransactionId(), response.getValorChave());
    }

    private void sendChavePixResponseToTopic(ChavePixResponse response, String topic){
        kafkaTemplate.send(topic, response.getTransactionId(), response);
        kafkaTemplate.flush();
    }
}


