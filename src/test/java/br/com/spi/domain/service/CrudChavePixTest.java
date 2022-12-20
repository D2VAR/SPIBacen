package br.com.spi.domain.service;

import br.com.spi.adapter.out.dynamo.entity.ChavePixDynamo;
import br.com.spi.exception.ChavePixNotFoundException;
import br.com.spi.infrastructure.dto.chave.ChavePixRequest;
import br.com.spi.infrastructure.dto.chave.ChavePixResponse;
import br.com.spi.infrastructure.enums.TipoChave;
import br.com.spi.infrastructure.mapper.ChavePixMapper;
import br.com.spi.port.out.DatabaseOutputPort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CrudChavePixTest {

    @Mock
    private DatabaseOutputPort repository;
    @Mock
    private ChavePixMapper mapper;
    @InjectMocks
    private CrudChavePix crudChavePix;
    ChavePixResponse response;
    ChavePixRequest request;
    ChavePixDynamo chaveDynamo;
    String valorChave;

    @BeforeEach
    void setUp(){
        startDTOs();
    }

    @Test
    void getChavePix() {

        when(repository.getChavePix(valorChave)).thenReturn(Optional.of(chaveDynamo));
        when(mapper.databaseToResponse(chaveDynamo)).thenReturn(response);

        ChavePixResponse resultado = crudChavePix.getChavePix(valorChave);
        Assertions.assertEquals(response.getTransactionId(),resultado.getTransactionId());
    }

    @Test
    void getChavePixException() {

        when(repository.getChavePix(valorChave)).thenReturn(Optional.empty());

        ChavePixNotFoundException ex = assertThrows(ChavePixNotFoundException.class, () -> crudChavePix.getChavePix(valorChave));
        Assertions.assertEquals("Chave PIX does not exist!", ex.getMessage());
    }


    @Test
    void deleteChavePix() {
        when(repository.getChavePix(valorChave)).thenReturn(Optional.of(chaveDynamo));
        doNothing().when(repository).deleteChavePix(chaveDynamo);

        crudChavePix.deleteChavePix(valorChave);

        verify(repository,times(1)).getChavePix(any());
        verify(repository,times(1)).deleteChavePix(any());
    }

    @Test
    void saveChavePix() {
        when(mapper.requestToDatabase(request)).thenReturn(chaveDynamo);
        doNothing().when(repository).saveChavePix(chaveDynamo);

        crudChavePix.saveChavePix(request);
        verify(mapper,times(1)).requestToDatabase(any());
        verify(repository,times(1)).saveChavePix(any());
    }

    private void startDTOs() {
        request = new ChavePixRequest("dd09838c-8a32-4a4c-8d4e-e3d0078719bc","341",
                "3213214","4040","33344455567","cliente", TipoChave.EMAIL,"cliente@teste.com");
        response = new ChavePixResponse("dd09838c-8a32-4a4c-8d4e-e3d0078719bc","341",
                "3213214","4040","33344455567","cliente", TipoChave.EMAIL,"cliente@teste.com");
        chaveDynamo = new ChavePixDynamo("dd09838c-8a32-4a4c-8d4e-e3d0078719bc","341",
                "3213214","4040","33344455567","cliente", TipoChave.EMAIL,"cliente@teste.com");
        valorChave = "cliente@teste.com";
    }
}