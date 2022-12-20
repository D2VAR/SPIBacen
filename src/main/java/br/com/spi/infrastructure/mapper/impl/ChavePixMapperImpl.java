package br.com.spi.infrastructure.mapper.impl;

import br.com.spi.adapter.out.dynamo.entity.ChavePixDynamo;
import br.com.spi.infrastructure.dto.chave.ChavePixRequest;
import br.com.spi.infrastructure.dto.chave.ChavePixResponse;
import br.com.spi.infrastructure.mapper.ChavePixMapper;
import br.com.spi.domain.model.ChavePix;
import org.springframework.stereotype.Component;


import static br.com.spi.infrastructure.validator.Validator.validateObject;

@Component
public class ChavePixMapperImpl implements ChavePixMapper{
    @Override
    public ChavePix databaseToModel(ChavePixDynamo entity){
        validateObject(entity);
        return new ChavePix(
                null,
                entity.getCodBanco(),
                entity.getCpfCnpjValorChave(),
                entity.getNumeroConta(),
                entity.getAgenciaConta(),
                entity.getCpfCnpj(),
                entity.getNome(),
                entity.getTipoChave(),
                entity.getValorChave()
        );
    }

    public ChavePixResponse modelToResponse(ChavePix chavePix){
        validateObject(chavePix);
        return ChavePixResponse.builder()
                .transactionId(chavePix.getTransactionId())
                .codBanco(chavePix.getCodBanco())
                .cpfCnpj(chavePix.getCpfCnpj())
                .numeroConta(chavePix.getNumeroConta())
                .agenciaConta(chavePix.getAgenciaConta())
                .nome(chavePix.getNome())
                .tipoChave(chavePix.getTipoChave())
                .valorChave(chavePix.getValorChave())
                .build();
    }

    @Override
    public ChavePixDynamo modelToDatabase(ChavePix chavePix){
        validateObject(chavePix);
        return ChavePixDynamo.builder()
                .codBanco(chavePix.getCodBanco())
                .cpfCnpjValorChave(chavePix.getCpfCnpjValorChave())
                .cpfCnpj(chavePix.getCpfCnpj())
                .numeroConta(chavePix.getNumeroConta())
                .agenciaConta(chavePix.getAgenciaConta())
                .nome(chavePix.getNome())
                .tipoChave(chavePix.getTipoChave())
                .valorChave(chavePix.getValorChave())
                .build();
    }

    @Override
    public ChavePix requestToModel(ChavePixRequest chavePixRequest){
        validateObject(chavePixRequest);
        return new ChavePix(
                chavePixRequest.getTransactionId(),
                chavePixRequest.getCodBanco(),
                chavePixRequest.getNumeroConta(),
                chavePixRequest.getAgenciaConta(),
                chavePixRequest.getCpfCnpj(),
                chavePixRequest.getNome(),
                chavePixRequest.getTipoChave(),
                chavePixRequest.getValorChave()
        );
    }

    @Override
    public ChavePixDynamo requestToDatabase(ChavePixRequest chavePixRequest){
        var model = requestToModel(chavePixRequest);
        return modelToDatabase(model);
    }

    @Override
    public ChavePixResponse databaseToResponse(ChavePixDynamo entity){
        var model = databaseToModel(entity);
        return modelToResponse(model);
    }

    @Override
    public ChavePixResponse requestToResponse(ChavePixRequest chavePixRequest){
        var model = requestToModel(chavePixRequest);
        return modelToResponse(model);
    }
}
