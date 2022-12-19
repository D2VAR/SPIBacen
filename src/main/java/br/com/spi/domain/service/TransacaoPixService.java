package br.com.spi.domain.service;

import br.com.spi.exception.ChavePixNotFoundException;
import br.com.spi.infrastructure.dto.transacao.TransacaoPixRequest;
import br.com.spi.infrastructure.dto.transacao.TransacaoValidadaRequest;
import br.com.spi.infrastructure.mapper.TransacaoPixMapper;
import br.com.spi.port.in.CrudChavePixInputPort;
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
    private final CrudChavePixInputPort chavePixInputPort;
    private final TransacaoPixMapper mapper;

    @Override
    public void enviarTransacaoPix(TransacaoPixRequest request) {

        var response = mapper.requestToResponse(request);
        var keyExists = chavePixInputPort.chavePixExists(request.getChaveDestino());
        if(!keyExists.getChaveExists()){
            throw new ChavePixNotFoundException("Falha na transação. Chave destino não localizada.");
        }
        transacaoPixOutputPort.enviarPix(response);
    }

    @Override
    public void retornarValidacao(TransacaoValidadaRequest request) {
        var response = mapper.validacaoToResponse(request);
        if (response.getPixRealizado().equals(Boolean.FALSE)){
            validacaoOutputPort.notificaFalha(response);
            return;
        }
        validacaoOutputPort.notificaSucesso(response);
    }
}
