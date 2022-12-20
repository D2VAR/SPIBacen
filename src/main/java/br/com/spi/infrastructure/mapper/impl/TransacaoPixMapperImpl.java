package br.com.spi.infrastructure.mapper.impl;

import br.com.spi.infrastructure.dto.transacao.PixTransferRequest;
import br.com.spi.infrastructure.dto.transacao.PixTransferResponse;
import br.com.spi.infrastructure.dto.transacao.TransacaoValidadaRequest;
import br.com.spi.infrastructure.dto.transacao.TransacaoValidadaResponse;
import br.com.spi.infrastructure.mapper.TransacaoPixMapper;
import org.springframework.stereotype.Component;

import static br.com.spi.infrastructure.validator.Validator.validateObject;

@Component
public class TransacaoPixMapperImpl implements TransacaoPixMapper {

    @Override
    public TransacaoValidadaResponse validacaoToResponse(TransacaoValidadaRequest request) {
        validateObject(request);
        return new TransacaoValidadaResponse(
                request.getTransactionId(),
                request.isPixRealizado(),
                request.getNome(),
                request.getCpfCnpj(),
                request.getTipoChave(),
                request.getChaveDestino(),
                request.getValor(),
                request.getCodBancoOrigem()
        );
    }

    @Override
    public PixTransferResponse requestToResponse(PixTransferRequest request) {
        validateObject(request);
        return new PixTransferResponse(
                request.getTransactionId(),
                request.getNome(),
                request.getCpfCnpj(),
                request.getTipoChave(),
                request.getChaveDestino(),
                request.getValor(),
                request.getCodBancoOrigem()
        );
    }
}
