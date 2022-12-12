package br.com.spi.domain.mapper.impl;

import br.com.spi.adapter.out.dynamo.entity.ChavePixEntity;
import br.com.spi.domain.dto.ChavePixResponse;
import br.com.spi.domain.mapper.ChavePixMapper;
import br.com.spi.domain.model.ChavePix;
import org.springframework.stereotype.Component;

@Component
public class ChavePixMapperImpl implements ChavePixMapper {
    public ChavePix entityToModel(ChavePixEntity entity) {
        if(entity == null){
            return null;
        }

        ChavePix chavePix = new ChavePix();
        chavePix.setCodBanco(entity.getCodBanco());
        chavePix.setNumeroConta(entity.getNumeroConta());
        chavePix.setAgenciaConta(entity.getAgenciaConta());
        chavePix.setCpfCnpj(entity.getCpfCnpj());
        chavePix.setNome(entity.getNome());
        chavePix.setTipoChave(entity.getTipoChave());
        chavePix.setValorChave(entity.getValorChave());

        return chavePix;
    }

    public ChavePixResponse modelToResponse(ChavePix chavePix) {
        if(chavePix == null){
            return null;
        }

        ChavePixResponse response = new ChavePixResponse();
        response.setCodBanco(chavePix.getCodBanco());
        response.setNumeroConta(chavePix.getNumeroConta());
        response.setAgenciaConta(chavePix.getAgenciaConta());
        response.setCpfCnpj(chavePix.getCpfCnpj());
        response.setNome(chavePix.getNome());
        response.setTipoChave(chavePix.getTipoChave());
        response.setValorChave(chavePix.getValorChave());

        return response;
    }
}
