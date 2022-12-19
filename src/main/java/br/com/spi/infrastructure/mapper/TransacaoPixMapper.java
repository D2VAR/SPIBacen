package br.com.spi.infrastructure.mapper;

import br.com.spi.infrastructure.dto.transacao.TransacaoPixRequest;
import br.com.spi.infrastructure.dto.transacao.TransacaoPixResponse;
import br.com.spi.infrastructure.dto.transacao.TransacaoValidadaRequest;
import br.com.spi.infrastructure.dto.transacao.TransacaoValidadaResponse;

public interface TransacaoPixMapper {
    TransacaoValidadaResponse validacaoToResponse(TransacaoValidadaRequest transacaoPixRequest);
    TransacaoPixResponse requestToResponse(TransacaoPixRequest transacaoPixRequest);
}
