package br.com.spi.infrastructure.mapper;

import br.com.spi.infrastructure.dto.transacao.PixTransferRequest;
import br.com.spi.infrastructure.dto.transacao.PixTransferResponse;
import br.com.spi.infrastructure.dto.transacao.TransacaoValidadaRequest;
import br.com.spi.infrastructure.dto.transacao.TransacaoValidadaResponse;

public interface TransacaoPixMapper {
    TransacaoValidadaResponse validacaoToResponse(TransacaoValidadaRequest transacaoPixRequest);
    PixTransferResponse requestToResponse(PixTransferRequest pixTransferRequest);
}
