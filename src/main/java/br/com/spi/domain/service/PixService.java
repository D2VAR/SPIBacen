package br.com.spi.domain.service;

import br.com.spi.infrastructure.dto.transacao.PixTransferRequest;
import br.com.spi.infrastructure.dto.transacao.TransacaoValidadaRequest;
import br.com.spi.infrastructure.dto.transacao.TransacaoValidadaResponse;
import br.com.spi.infrastructure.mapper.TransacaoPixMapper;
import br.com.spi.port.in.ChavePixInput;
import br.com.spi.port.in.PixTransferReceive;
import br.com.spi.port.out.PixTransferValidation;
import br.com.spi.port.in.ValidacaoTransacaoInputPort;
import br.com.spi.port.out.PixTransferFinalization;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PixService implements PixTransferReceive, ValidacaoTransacaoInputPort {

    private final PixTransferValidation pixValidation;
    private final PixTransferFinalization pixFinalization;
    private final ChavePixInput chavePixInputPort;
    private final TransacaoPixMapper mapper;

    @Override
    public void validatePix(PixTransferRequest request) {
        var chavePixExists = chavePixInputPort.chavePixExists(request.getChaveDestino());
        var response = mapper.requestToResponse(request);
        if(chavePixExists){
            pixValidation.enviarPix(response);
        }
        else{
            var failureResponse = new TransacaoValidadaResponse(response);
            pixFinalization.notificaFalha(failureResponse);
        }

    }

    @Override
    public void retornarValidacao(TransacaoValidadaRequest request) {
        var response = mapper.validacaoToResponse(request);
        if (response.isPixRealizado()){
            pixFinalization.notificaSucesso(response);
            return;
        }
        pixFinalization.notificaFalha(response);
    }
}
