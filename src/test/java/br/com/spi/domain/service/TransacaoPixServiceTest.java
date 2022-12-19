package br.com.spi.domain.service;

import br.com.spi.exception.ChavePixNotFoundException;
import br.com.spi.infrastructure.dto.chave.ChavePixExistsResponse;
import br.com.spi.infrastructure.dto.transacao.TransacaoPixRequest;
import br.com.spi.infrastructure.dto.transacao.TransacaoPixResponse;
import br.com.spi.infrastructure.dto.transacao.TransacaoValidadaRequest;
import br.com.spi.infrastructure.dto.transacao.TransacaoValidadaResponse;
import br.com.spi.infrastructure.enums.TipoChave;
import br.com.spi.infrastructure.mapper.TransacaoPixMapper;
import br.com.spi.port.in.CrudChavePixInputPort;
import br.com.spi.port.out.TransacaoPixOutputPort;
import br.com.spi.port.out.ValidacaoTransacaoOutputPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class TransacaoPixServiceTest {

    @Mock
    private TransacaoPixOutputPort transacaoPixOutputPort;
    @Mock
    private ValidacaoTransacaoOutputPort validacaoOutputPort;
    @Mock
    private CrudChavePixInputPort chavePixInputPort;
    @Mock
    private TransacaoPixMapper mapper;
    @InjectMocks
    TransacaoPixService service;

    TransacaoPixRequest request;
    TransacaoPixResponse response;
    TransacaoValidadaRequest validacaoRequest;
    TransacaoValidadaResponse validacaoResponse;
    ChavePixExistsResponse existsResponse;

    @BeforeEach
    void setUp(){
        startDTOs();
    }

    @Test
    void enviarTransacaoPixSuccess() {

        when(mapper.requestToResponse(request)).thenReturn(response);
        when(chavePixInputPort.chavePixExists(request.getChaveDestino())).thenReturn(existsResponse);
        doNothing().when(transacaoPixOutputPort).enviarPix(response);

        service.enviarTransacaoPix(request);

        verify(mapper, times(1)).requestToResponse(request);
        verify(chavePixInputPort, times(1)).chavePixExists(request.getChaveDestino());
        verify(transacaoPixOutputPort, times(1)).enviarPix(response);
    }

    @Test
    void enviarTransacaoPixFail() {
        existsResponse.setChaveExists(Boolean.FALSE);
        when(mapper.requestToResponse(request)).thenReturn(response);
        when(chavePixInputPort.chavePixExists(request.getChaveDestino())).thenReturn(existsResponse);

        ChavePixNotFoundException ex = assertThrows(ChavePixNotFoundException.class, () -> service.enviarTransacaoPix(request));

        verify(mapper, times(1)).requestToResponse(request);
        verify(chavePixInputPort, times(1)).chavePixExists(request.getChaveDestino());
        assertEquals("Falha na transação. Chave destino não localizada.", ex.getMessage());
    }

    @Test
    void retornarValidacaoSucesso() {
        when(mapper.validacaoToResponse(validacaoRequest)).thenReturn(validacaoResponse);
        doNothing().when(validacaoOutputPort).notificaSucesso(validacaoResponse);

        service.retornarValidacao(validacaoRequest);

        verify(mapper, times(1)).validacaoToResponse(validacaoRequest);
        verify(validacaoOutputPort, times(1)).notificaSucesso(validacaoResponse);
    }

    @Test
    void retornarValidacaoFalha() {
        validacaoResponse.setPixRealizado(Boolean.FALSE);
        when(mapper.validacaoToResponse(validacaoRequest)).thenReturn(validacaoResponse);
        doNothing().when(validacaoOutputPort).notificaFalha(validacaoResponse);

        service.retornarValidacao(validacaoRequest);

        verify(mapper, times(1)).validacaoToResponse(validacaoRequest);
        verify(validacaoOutputPort, times(1)).notificaFalha(validacaoResponse);
    }

    private void startDTOs(){
        validacaoRequest = new TransacaoValidadaRequest("dd09838c-8a32-4a4c-8d4e-e3d0078719bc",Boolean.TRUE,"cliente","33344455567",
                TipoChave.EMAIL,"recebedor@teste.com", BigDecimal.valueOf(100.50),"341");
        validacaoResponse = new TransacaoValidadaResponse("dd09838c-8a32-4a4c-8d4e-e3d0078719bc",Boolean.TRUE,"cliente","33344455567",
                TipoChave.EMAIL,"recebedor@teste.com", BigDecimal.valueOf(100.50),"341");
        request = new TransacaoPixRequest("dd09838c-8a32-4a4c-8d4e-e3d0078719bc","cliente","33344455567",
                TipoChave.EMAIL,"recebedor@teste.com", BigDecimal.valueOf(100.50),"341");
        response = new TransacaoPixResponse("dd09838c-8a32-4a4c-8d4e-e3d0078719bc","cliente","33344455567",
                TipoChave.EMAIL,"recebedor@teste.com", BigDecimal.valueOf(100.50),"341");
        existsResponse = new ChavePixExistsResponse("341", Boolean.TRUE, TipoChave.EMAIL,"cliente@teste.com");
    }
}