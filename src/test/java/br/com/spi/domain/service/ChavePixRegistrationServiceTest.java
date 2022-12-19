package br.com.spi.domain.service;

import br.com.spi.infrastructure.dto.chave.ChavePixExistsResponse;
import br.com.spi.infrastructure.dto.chave.ChavePixRequest;
import br.com.spi.infrastructure.dto.chave.ChavePixResponse;
import br.com.spi.infrastructure.enums.TipoChave;
import br.com.spi.infrastructure.mapper.ChavePixMapper;
import br.com.spi.port.in.CrudChavePixInputPort;
import br.com.spi.port.out.ChavePixRegistrationOutputPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ChavePixRegistrationServiceTest {

    @Mock
    private ChavePixMapper mapper;
    @Mock
    private CrudChavePixInputPort inputPort;
    @Mock
    private ChavePixRegistrationOutputPort outputPort;
    @InjectMocks
    private ChavePixRegistrationService service;

    ChavePixRequest request;
    ChavePixResponse response;
    ChavePixExistsResponse existsResponse;

    @BeforeEach
    void setUp(){
        startDTO();
    }
    @Test
    void registerChavePixSuccess(){

        when(mapper.requestToResponse(request)).thenReturn(response);
        when(inputPort.chavePixExists(request.getValorChave())).thenReturn(existsResponse);
        doNothing().when(inputPort).saveChavePix(request);
        doNothing().when(outputPort).notifySuccessfulRegistration(response);

        service.registerChavePix(request);

        verify(mapper, times(1)).requestToResponse(request);
        verify(inputPort, times(1)).chavePixExists(request.getValorChave());
        verify(inputPort, times(1)).saveChavePix(request);
        verify(outputPort, times(1)).notifySuccessfulRegistration(response);
    }

    @Test
    void registerChavePixFail(){
        ChavePixRequest request = new ChavePixRequest("dd09838c-8a32-4a4c-8d4e-e3d0078719bc","341",
                "3213214","4040","33344455567","cliente", TipoChave.EMAIL,"cliente@teste.com");
        ChavePixResponse response = new ChavePixResponse("dd09838c-8a32-4a4c-8d4e-e3d0078719bc","341",
                "3213214","4040","33344455567","cliente", TipoChave.EMAIL,"cliente@teste.com");
        ChavePixExistsResponse existsResponse = new ChavePixExistsResponse("341", Boolean.TRUE,TipoChave.EMAIL,"cliente@teste.com");

        when(mapper.requestToResponse(request)).thenReturn(response);
        when(inputPort.chavePixExists(request.getValorChave())).thenReturn(existsResponse);
        doNothing().when(outputPort).notifyRegistrationFailure(response);

        service.registerChavePix(request);

        verify(mapper, times(1)).requestToResponse(request);
        verify(inputPort, times(1)).chavePixExists(request.getValorChave());
        verify(outputPort, times(1)).notifyRegistrationFailure(response);
    }

    private void startDTO(){
        request = new ChavePixRequest("dd09838c-8a32-4a4c-8d4e-e3d0078719bc","341",
                "3213214","4040","33344455567","cliente", TipoChave.EMAIL,"cliente@teste.com");
        response = new ChavePixResponse("dd09838c-8a32-4a4c-8d4e-e3d0078719bc","341",
                "3213214","4040","33344455567","cliente", TipoChave.EMAIL,"cliente@teste.com");
        existsResponse = new ChavePixExistsResponse("341", Boolean.FALSE,TipoChave.EMAIL,"cliente@teste.com");
    }
}