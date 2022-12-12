package br.com.spi.domain.mapper;

import br.com.spi.adapter.out.dynamo.entity.ChavePixEntity;
import br.com.spi.domain.dto.ChavePixResponse;
import br.com.spi.domain.model.ChavePix;

public interface ChavePixMapper {
    ChavePix entityToModel(ChavePixEntity entity);

    ChavePixResponse modelToResponse(ChavePix chavePix);
}
