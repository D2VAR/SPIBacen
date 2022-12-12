package br.com.bacen.domain.mapper;

import br.com.bacen.adapter.out.dynamo.entity.ChavePixEntity;
import br.com.bacen.domain.dto.ChavePixResponse;
import br.com.bacen.domain.model.ChavePix;

public interface ChavePixMapper {
    ChavePix modelToDomain(ChavePixEntity model);

    ChavePixResponse domainToResponse(ChavePix chavePix);
}
