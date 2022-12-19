package br.com.spi.infrastructure.mapper;

import br.com.spi.infrastructure.dto.transacao.TransacaoPixRequest;
import br.com.spi.infrastructure.dto.transacao.TransacaoPixResponse;

public interface TransacaoPixMapper {
    TransacaoPixResponse requestToResponse(TransacaoPixRequest transacaoPixRequest);
}
