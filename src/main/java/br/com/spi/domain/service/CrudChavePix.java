package br.com.spi.domain.service;

import br.com.spi.infrastructure.dto.ChavePixRequest;
import br.com.spi.adapter.out.dynamo.entity.ChavePixDynamo;
import br.com.spi.exception.ChavePixNotFoundException;
import br.com.spi.infrastructure.dto.ChavePixResponse;
import br.com.spi.infrastructure.mapper.ChavePixMapper;
import br.com.spi.port.in.CrudChavePixInputPort;
import br.com.spi.port.out.DatabaseOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import static br.com.spi.infrastructure.validator.Validator.validateObject;
import static br.com.spi.infrastructure.validator.Validator.validateString;

@Service
@RequiredArgsConstructor
public class CrudChavePix implements CrudChavePixInputPort{
    private final DatabaseOutputPort repository;
    private final ChavePixMapper mapper;

    @Override
    public ChavePixResponse getChavePix(String valorChave){
        var entity = getChavePixEntity(valorChave);
        return mapper.databaseToResponse(entity);
    }

    private ChavePixDynamo getChavePixEntity(String valorChave){
        validateString(valorChave);
        var databaseResponse = repository.getChavePix(valorChave);
        return databaseResponse.orElseThrow(
                () -> new ChavePixNotFoundException("Chave PIX does not exist!"));

    }


    @Override
    public boolean chavePixExists(String valorChave){
        try{
            getChavePixEntity(valorChave);
            return true;
        } catch (ChavePixNotFoundException ex){
            return false;
        }
    }

    @Override
    public void updateChavePix(ChavePixRequest request){
        validateObject(request);
        deleteChavePix(request.getValorChave());
        saveChavePix(request);
    }

    @Override
    public void deleteChavePix(String valorChave){
        var entity = getChavePixEntity(valorChave);
        repository.deleteChavePix(entity);
    }

    @Override
    public void saveChavePix(ChavePixRequest request){
        validateObject(request);
        var entity = mapper.requestToDatabase(request);
        repository.saveChavePix(entity);
    }
}
