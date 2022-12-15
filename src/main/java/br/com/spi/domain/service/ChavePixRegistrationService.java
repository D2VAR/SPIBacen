package br.com.spi.domain.service;

import br.com.spi.infrastructure.dto.ChavePixRequest;
import br.com.spi.infrastructure.mapper.ChavePixMapper;
import br.com.spi.port.in.ChavePixRegistrationInputPort;
import br.com.spi.port.in.CrudChavePixInputPort;
import br.com.spi.port.out.ChavePixRegistrationOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChavePixRegistrationService implements ChavePixRegistrationInputPort{

    private final ChavePixMapper mapper;
    private final CrudChavePixInputPort crudChavePixInputPort;
    private final ChavePixRegistrationOutputPort cadastroOutputPort;


    @Override
    public void registerChavePix(ChavePixRequest chavePixRequest) {
        var model = mapper.requestToResponse(chavePixRequest);
        if (crudChavePixInputPort.chavePixExists(chavePixRequest.getValorChave())){
            crudChavePixInputPort.saveChavePix(chavePixRequest);
            cadastroOutputPort.notifySuccessfulRegistration(model);
            return;
        }
        cadastroOutputPort.notifyRegistrationFailure(model);
    }
}
