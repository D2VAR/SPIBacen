package br.com.spi.infrastructure.mapper.impl;

import br.com.spi.infrastructure.dto.transacao.TransacaoPixRequest;
import br.com.spi.infrastructure.dto.transacao.TransacaoPixResponse;
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
                request.getPixRealizado(),
                request.getNome(),
                request.getCpfCnpj(),
                request.getChaveDestino(),
                request.getValor(),
                request.getCodBancoDestino()
        );
    }

    @Override
    public TransacaoPixResponse requestToResponse(TransacaoPixRequest request) {
        validateObject(request);
        return new TransacaoPixResponse(
                request.getTransactionId(),
                request.getNome(),
                request.getCpfCnpj(),
                request.getChaveDestino(),
                request.getValor(),
                request.getCodBancoDestino()
        );
    }
}
