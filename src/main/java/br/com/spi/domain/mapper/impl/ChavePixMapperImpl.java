package br.com.spi.domain.mapper.impl;

import br.com.spi.adapter.out.dynamo.entity.ChavePixDynamo;
import br.com.spi.domain.mapper.ChavePixMapper;
import br.com.spi.domain.model.dto.ChavePixDto;
import org.springframework.stereotype.Component;

@Component
public class ChavePixMapperImpl implements ChavePixMapper {
    public ChavePixDto entityToDto(ChavePixDynamo entity) {
        if(entity == null)
            return null;

        ChavePixDto chavePixDto = new ChavePixDto();
        chavePixDto.setCodBanco(entity.getCodBanco());
        chavePixDto.setNumeroConta(entity.getNumeroConta());
        chavePixDto.setAgenciaConta(entity.getAgenciaConta());
        chavePixDto.setCpfCnpj(entity.getCpfCnpj());
        chavePixDto.setNome(entity.getNome());
        chavePixDto.setTipoChave(entity.getTipoChave());
        chavePixDto.setValorChave(entity.getValorChave());
        return chavePixDto;
    }

    public ChavePixDynamo dtoToEntity(ChavePixDto chavePixDto) {
        if(chavePixDto == null)
            return null;

        ChavePixDynamo response = new ChavePixDynamo();
        response.setCodBanco(chavePixDto.getCodBanco());
        response.setCpfCnpjValorChave(chavePixDto.getCpgCnpjValorChave());
        response.setNumeroConta(chavePixDto.getNumeroConta());
        response.setAgenciaConta(chavePixDto.getAgenciaConta());
        response.setCpfCnpj(chavePixDto.getCpfCnpj());
        response.setNome(chavePixDto.getNome());
        response.setTipoChave(chavePixDto.getTipoChave());
        response.setValorChave(chavePixDto.getValorChave());
        return response;
    }
}
