package br.com.bacen.domain.mapper.impl;

import br.com.bacen.adapter.out.dynamo.entity.ChavePixEntity;
import br.com.bacen.domain.dto.ChavePixResponse;
import br.com.bacen.domain.mapper.ChavePixMapper;
import br.com.bacen.domain.model.ChavePix;
import org.springframework.stereotype.Component;

@Component
public class ChavePixMapperImpl implements ChavePixMapper {
    public ChavePix modelToDomain(ChavePixEntity model) {
        if(model == null){
            return null;
        }

        ChavePix chavePix = new ChavePix();
        chavePix.setCodBanco(model.getCodBanco());
        chavePix.setNumeroConta(model.getNumeroConta());
        chavePix.setAgenciaConta(model.getAgenciaConta());
        chavePix.setCpfCnpj(model.getCpfCnpj());
        chavePix.setNome(model.getNome());
        chavePix.setTipoChave(model.getTipoChave());
        chavePix.setValorChave(model.getValorChave());

        return chavePix;
    }

    public ChavePixResponse domainToResponse(ChavePix chavePix) {
        if(chavePix == null){
            return null;
        }

        ChavePixResponse response = new ChavePixResponse();
        response.setCod_banco(chavePix.getCodBanco());
        response.setNumero_conta(chavePix.getNumeroConta());
        response.setAgencia_conta(chavePix.getAgenciaConta());
        response.setCpf_cnpj(chavePix.getCpfCnpj());
        response.setNome(chavePix.getNome());
        response.setTipo_chave(chavePix.getTipoChave());
        response.setValor_chave(chavePix.getValorChave());

        return response;
    }
}
