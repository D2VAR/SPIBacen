package br.com.spi.adapter.out.producer;

import br.com.spi.domain.dto.ChavePixCadastroMensagem;
import br.com.spi.domain.model.ChavePix;
import br.com.spi.infrastructure.mapper.ChavePixMapper;
import br.com.spi.port.out.CadastroChaveOutputPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component

public class ProducerCadastroChave implements CadastroChaveOutputPort {

    @Value("${topic.name.retorno.success}")
    private String topicSuccess;

    @Value("${topic.name.retorno.fail}")
    private String topicFail;

    private final KafkaTemplate<String,ChavePixCadastroMensagem> kafkaTemplate;
    private final ChavePixMapper chavePixMapper;

    public ProducerCadastroChave(KafkaTemplate<String, ChavePixCadastroMensagem> kafkaTemplate, ChavePixMapper chavePixMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.chavePixMapper = chavePixMapper;
    }

    @Override
    public void notificaCadastroConcluido(ChavePix novaChavePix) {
        ChavePixCadastroMensagem mensagem = chavePixMapper.domainToMensagem(novaChavePix);

        mensagem.setId(UUID.randomUUID().toString());
        mensagem.setStatus("SUCESSO");


        kafkaTemplate.send(topicSuccess, mensagem);
        log.info("#### Chave pix cadastrada - mensagem: {}",mensagem);

        kafkaTemplate.flush();
    }

    @Override
    public void notificaErroCadastro(ChavePix novaChavePix) {
        ChavePixCadastroMensagem mensagem = chavePixMapper.domainToMensagem(novaChavePix);
        mensagem.setId(UUID.randomUUID().toString());
        mensagem.setStatus("Falha ao cadastrar chave pix .");

        kafkaTemplate.send(this.topicFail, mensagem);
        log.info("#### Erro cadastro chave pix - mensagem: {}",mensagem);

        kafkaTemplate.flush();
    }
}


