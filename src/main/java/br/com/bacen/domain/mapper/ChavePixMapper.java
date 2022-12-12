package br.com.bacen.domain.mapper;

import br.com.itau.verificachavepix.application.dto.ChavePixResponse;
import br.com.itau.verificachavepix.domain.ChavePix;
import br.com.itau.verificachavepix.infrastructure.persistence.model.ChavePixModel;

public interface ChavePixMapper {
    //TODO: VERIFICAR SE HA USO CORRETO DE INTERFACE + IMPL
    ChavePix modelToDomain(ChavePixModel model);

    ChavePixResponse domainToResponse(ChavePix chavePix);
}
