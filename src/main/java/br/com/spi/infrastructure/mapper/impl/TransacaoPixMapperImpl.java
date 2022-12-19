package br.com.spi.infrastructure.mapper.impl;

import br.com.spi.infrastructure.dto.transacao.TransacaoPixRequest;
import br.com.spi.infrastructure.dto.transacao.TransacaoPixResponse;
import br.com.spi.infrastructure.mapper.TransacaoPixMapper;
import org.springframework.stereotype.Component;

import static br.com.spi.infrastructure.validator.Validator.validateObject;

@Component
public class TransacaoPixMapperImpl implements TransacaoPixMapper {

    @Override
    public TransacaoPixResponse requestToResponse(TransacaoPixRequest transacaoPixRequest) {
        validateObject(transacaoPixRequest);
        return new TransacaoPixResponse(
                transacaoPixRequest.getTransactionId(),
                transacaoPixRequest.getNome(),
                transacaoPixRequest.getCpfCnpj(),
                transacaoPixRequest.getChaveDestino(),
                transacaoPixRequest.getValor(),
                transacaoPixRequest.getCodBancoDestino()
        );
    }
}
