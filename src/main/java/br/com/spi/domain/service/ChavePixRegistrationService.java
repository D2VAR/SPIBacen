package br.com.spi.domain.service;

import br.com.spi.infrastructure.dto.chave.ChavePixRequest;
import br.com.spi.infrastructure.mapper.ChavePixMapper;
import br.com.spi.port.in.ChavePixRegistration;
import br.com.spi.port.in.ChavePixInput;
import br.com.spi.port.out.ChavePixRegistrationNotify;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChavePixRegistrationService implements ChavePixRegistration{

    private final ChavePixMapper mapper;
    private final ChavePixInput chavePixInput;
    private final ChavePixRegistrationNotify cadastroOutputPort;


    @Override
    public void registerChavePix(ChavePixRequest chavePixRequest) {
        var response = mapper.requestToResponse(chavePixRequest);
        var chavePixExists = chavePixInput.chavePixExists(chavePixRequest.getValorChave());
        if (chavePixExists){
            cadastroOutputPort.notifyRegistrationFailure(response);
            return;
        }
        chavePixInput.saveChavePix(chavePixRequest);
        cadastroOutputPort.notifySuccessfulRegistration(response);
    }
}
