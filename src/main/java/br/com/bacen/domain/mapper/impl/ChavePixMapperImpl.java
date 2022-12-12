package br.com.bacen.domain.mapper.impl;

import br.com.bacen.domain.dto.ChavePixResponse;
import br.com.bacen.domain.mapper.ChavePixMapper;
import br.com.bacen.domain.model.ChavePix;
import br.com.itau.verificachavepix.application.dto.ChavePixResponse;
import br.com.itau.verificachavepix.application.mapper.ChavePixMapper;
import br.com.itau.verificachavepix.domain.ChavePix;
import br.com.itau.verificachavepix.infrastructure.persistence.model.ChavePixModel;

public class ChavePixMapperImpl implements ChavePixMapper {
    public ChavePix modelToDomain(ChavePixModel model) {
        if(model == null){
            return null;
        }

        ChavePix chavePix = new ChavePix();
        chavePix.setCod_banco(model.getCod_banco());
        chavePix.setNumero_conta(model.getNumero_conta());
        chavePix.setAgencia_conta(model.getAgencia_conta());
        chavePix.setCpf_cnpj(model.getCpf_cnpj());
        chavePix.setNome(model.getNome());
        chavePix.setTipo_chave(model.getTipo_chave());
        chavePix.setValor_chave(model.getValor_chave());

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
