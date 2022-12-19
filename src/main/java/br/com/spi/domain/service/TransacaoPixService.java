package br.com.spi.domain.service;

import br.com.spi.infrastructure.dto.transacao.TransacaoPixRequest;
import br.com.spi.infrastructure.mapper.TransacaoPixMapper;
import br.com.spi.port.in.TransacaoPixInputPort;
import br.com.spi.port.out.TransacaoPixOutputPort;
import br.com.spi.port.out.ValidacaoTransacaoInputPort;
import br.com.spi.port.out.ValidacaoTransacaoOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransacaoPixService implements TransacaoPixInputPort, ValidacaoTransacaoInputPort {

    private final TransacaoPixOutputPort transacaoPixOutputPort;
    private final ValidacaoTransacaoOutputPort validacaoOutputPort;

    private final TransacaoPixMapper mapper;

    @Override
    public void enviarTransacaoPix(TransacaoPixRequest request) {

        var response = mapper.requestToResponse(request);
        transacaoPixOutputPort.enviarPix(response);
    }

    @Override
    public void retornarSucesso(TransacaoPixRequest request) {
        var response = mapper.requestToResponse(request);
        validacaoOutputPort.notificaSucesso(response);
    }

    @Override
    public void retornarFalha(TransacaoPixRequest request) {
        var response = mapper.requestToResponse(request);
        validacaoOutputPort.notificaFalha(response);
    }
}
