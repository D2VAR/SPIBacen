package br.com.spi.infrastructure.mapper;

import br.com.spi.infrastructure.dto.chave.ChavePixRequest;
import br.com.spi.adapter.out.dynamo.entity.ChavePixDynamo;
import br.com.spi.infrastructure.dto.chave.ChavePixResponse;
import br.com.spi.domain.model.ChavePix;

public interface ChavePixMapper {
    ChavePix databaseToModel(ChavePixDynamo entity);
    ChavePixResponse modelToResponse(ChavePix chavePix);
    ChavePixDynamo modelToDatabase(ChavePix chavePix);
    ChavePix requestToModel(ChavePixRequest chavePixRequest);
    ChavePixDynamo requestToDatabase(ChavePixRequest chavePixRequest);
    ChavePixResponse databaseToResponse(ChavePixDynamo entity);
    ChavePixResponse requestToResponse(ChavePixRequest chavePixRequest);
}
