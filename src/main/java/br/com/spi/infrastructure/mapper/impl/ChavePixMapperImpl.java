package br.com.spi.infrastructure.mapper.impl;

import br.com.spi.adapter.out.dynamo.entity.ChavePixEntity;
import br.com.spi.domain.dto.ChavePixCadastroMensagem;
import br.com.spi.domain.dto.ChavePixDTO;
import br.com.spi.domain.dto.ChavePixResponse;
import br.com.spi.infrastructure.mapper.ChavePixMapper;
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

    @Override
    public ChavePix dtoToModel(ChavePixDTO chavePixDTO) {
        if(chavePixDTO == null){
            return null;
        }

        ChavePix chavePix = new ChavePix();
        chavePix.setCodBanco(Integer.parseInt(chavePixDTO.getCodBanco()));
        chavePix.setNumeroConta(chavePixDTO.getNumeroConta());
        chavePix.setAgenciaConta(Integer.parseInt(chavePixDTO.getAgenciaConta()));
        chavePix.setCpfCnpj(chavePixDTO.getCpfCnpj());
        chavePix.setNome(chavePixDTO.getNome());
        //chavePix.setTipoChave(chavePixDTO.getTipoChave());
        chavePix.setValorChave(chavePixDTO.getValorChave());

        return chavePix;
    }

    @Override
    public ChavePixEntity modeltoEntity(ChavePix chavePix) {
        if(chavePix == null){
            return null;
        }

        ChavePixEntity entity = new ChavePixEntity();
        entity.setCodBanco(chavePix.getCodBanco());
        entity.setNumeroConta(chavePix.getNumeroConta());
        entity.setAgenciaConta(chavePix.getAgenciaConta());
        entity.setCpfCnpj(chavePix.getCpfCnpj());
        entity.setNome(chavePix.getNome());
        entity.setTipoChave(chavePix.getTipoChave());
        entity.setValorChave(chavePix.getValorChave());

        return entity;
    }

    @Override
    public ChavePixCadastroMensagem domainToMensagem(ChavePix chavePix) {
        if(chavePix == null){
            return null;
        }

        ChavePixCadastroMensagem mensagem = new ChavePixCadastroMensagem();
        mensagem.setCodBanco(chavePix.getCodBanco().toString());
        mensagem.setNumeroConta(chavePix.getNumeroConta());
        mensagem.setAgenciaConta(chavePix.getAgenciaConta().toString());
        mensagem.setCpfCnpj(chavePix.getCpfCnpj());
        mensagem.setNome(chavePix.getNome());
        //mensagem.setTipoChave(chavePix.getTipoChave());
        mensagem.setValorChave(chavePix.getValorChave());

        return mensagem;
    }
}
