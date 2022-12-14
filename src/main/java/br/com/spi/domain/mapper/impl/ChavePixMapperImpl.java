package br.com.spi.domain.mapper.impl;

import br.com.spi.adapter.in.rest.controller.dto.ChavePixRequest;
import br.com.spi.adapter.out.dynamo.entity.ChavePixDynamo;
import br.com.spi.domain.mapper.ChavePixMapper;
import br.com.spi.domain.model.dto.ChavePixResponse;
import org.springframework.stereotype.Component;

@Component
public class ChavePixMapperImpl implements ChavePixMapper {
    @Override
    public ChavePixResponse entityToResponse(ChavePixDynamo entity) {
        if(entity == null)
            return null;

        return ChavePixResponse.builder()
                .codBanco(entity.getCodBanco())
                .cpgCnpjValorChave(entity.getCpfCnpjValorChave())
                .numeroConta(entity.getNumeroConta())
                .agenciaConta(entity.getAgenciaConta())
                .cpfCnpj(entity.getCpfCnpj())
                .nome(entity.getNome())
                .tipoChave(entity.getTipoChave())
                .valorChave(entity.getValorChave())
                .build();
    }
    @Override
    public ChavePixDynamo requestToEntity(ChavePixRequest request) {
        if(request == null)
            return null;

        return ChavePixDynamo.builder()
                .codBanco(request.getCodBanco())
                .cpfCnpjValorChave(request.getCpfCnpjValorChave())
                .numeroConta(request.getNumeroConta())
                .agenciaConta(request.getAgenciaConta())
                .cpfCnpj(request.getCpfCnpj())
                .nome(request.getNome())
                .tipoChave(request.getTipoChave())
                .valorChave(request.getValorChave())
                .build();
    }
}
