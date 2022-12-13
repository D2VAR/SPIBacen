package br.com.spi.domain.mapper;

import br.com.spi.adapter.out.dynamo.entity.ChavePixDynamo;
import br.com.spi.domain.model.dto.ChavePixDto;

public interface ChavePixMapper {
    ChavePixDto entityToDto(ChavePixDynamo entity);

    ChavePixDynamo dtoToEntity(ChavePixDto chavePixDto);
}
