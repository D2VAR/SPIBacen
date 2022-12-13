package br.com.spi.infrastructure.mapper;

import br.com.spi.adapter.out.dynamo.entity.ChavePixEntity;
import br.com.spi.domain.dto.ChavePixDTO;
import br.com.spi.domain.dto.ChavePixResponse;
import br.com.spi.domain.model.ChavePix;

public interface ChavePixMapper {
    ChavePix entityToModel(ChavePixEntity entity);

    ChavePixResponse modelToResponse(ChavePix chavePix);

    ChavePix dtoToModel(ChavePixDTO chavePixDTO);

    ChavePixEntity modeltoEntity(ChavePix chavePix);
}
