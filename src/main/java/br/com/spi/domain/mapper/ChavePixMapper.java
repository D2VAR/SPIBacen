package br.com.spi.domain.mapper;

import br.com.spi.adapter.in.rest.controller.dto.ChavePixRequest;
import br.com.spi.adapter.out.dynamo.entity.ChavePixDynamo;
import br.com.spi.domain.model.dto.ChavePixResponse;

public interface ChavePixMapper {
    ChavePixResponse entityToResponse(ChavePixDynamo entity);
    ChavePixDynamo requestToEntity(ChavePixRequest chavePixRequest);
}
