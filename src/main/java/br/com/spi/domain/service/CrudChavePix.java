package br.com.spi.domain.service;

import br.com.spi.adapter.in.rest.controller.dto.ChavePixRequest;
import br.com.spi.adapter.out.dynamo.entity.ChavePixDynamo;
import br.com.spi.adapter.out.dynamo.exception.ChavePixNotFoundException;
import br.com.spi.domain.exception.InvalidObjectException;
import br.com.spi.domain.exception.InvalidStringException;
import br.com.spi.domain.mapper.ChavePixMapper;
import br.com.spi.domain.model.dto.ChavePixResponse;
import br.com.spi.port.in.CrudChavePixInputPort;
import br.com.spi.port.out.DatabaseOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CrudChavePix implements CrudChavePixInputPort{
    private final DatabaseOutputPort repository;
    private final ChavePixMapper mapper;

    @Override
    public ChavePixResponse getChavePix(String valorChave){
        var entity = getChavePixEntity(valorChave);
        return mapper.entityToResponse(entity);
    }

    private ChavePixDynamo getChavePixEntity(String valorChave){
        validateString(valorChave);
        var databaseResponse = repository.getChavePix(valorChave);
        return databaseResponse.orElseThrow(() -> new ChavePixNotFoundException("Chave PIX does not exist!"));
    }

    private void validateString(String stringToBeValidated){
        if (stringIsValid(stringToBeValidated))
            return;
        throw new InvalidStringException("String is invalid!");
    }

    private boolean stringIsValid(String stringToBeValidated){
        return Objects.nonNull(stringToBeValidated) && !stringToBeValidated.isBlank();
    }

    @Override
    public boolean chavePixExists(String valorChave){
        try{
            getChavePixEntity(valorChave);
        } catch (ChavePixNotFoundException ex){
            return false;
        }
        return true;
    }

    @Override
    public void updateChavePix(ChavePixRequest request){
        validateObject(request);
        deleteChavePix(request.getValorChave());
        saveChavePix(request);
    }

    private void validateObject(Object objectToBeValidated){
        if (objectIsValid(objectToBeValidated))
            return;
        throw new InvalidObjectException("Object is invalid!");
    }

    private boolean objectIsValid(Object objectToBeValidated){
        return Objects.nonNull(objectToBeValidated);
    }

    @Override
    public void deleteChavePix(String valorChave){
        var entity = getChavePixEntity(valorChave);
        repository.deleteChavePix(entity);
    }

    @Override
    public void saveChavePix(ChavePixRequest request){
        validateObject(request);
        var entity = mapper.requestToEntity(request);
        repository.saveChavePix(entity);
    }
}
