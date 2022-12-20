package br.com.spi.domain.service;

import br.com.spi.infrastructure.dto.chave.ChavePixExistsResponse;
import br.com.spi.infrastructure.dto.chave.ChavePixRequest;
import br.com.spi.adapter.out.dynamo.entity.ChavePixDynamo;
import br.com.spi.exception.ChavePixNotFoundException;
import br.com.spi.infrastructure.dto.chave.ChavePixResponse;
import br.com.spi.infrastructure.mapper.ChavePixMapper;
import br.com.spi.port.in.ChavePixInput;
import br.com.spi.port.out.DatabaseAccess;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import static br.com.spi.infrastructure.validator.Validator.validateObject;
import static br.com.spi.infrastructure.validator.Validator.validateString;

@Service
@RequiredArgsConstructor
public class ChavePixService implements ChavePixInput{
    private final DatabaseAccess repository;
    private final ChavePixMapper mapper;

    @Override
    public ChavePixResponse getChavePix(String valorChave){
        var entity = getChavePixEntity(valorChave);
        return mapper.databaseToResponse(entity);
    }

    private ChavePixDynamo getChavePixEntity(String valorChave){
        validateString(valorChave);
        return repository.getChavePix(valorChave).orElseThrow(
                () -> new ChavePixNotFoundException("Chave PIX does not exist!"));

    }

    public boolean chavePixExists(String valorChave){
        try{
            getChavePixEntity(valorChave);
            return true;
        }catch (ChavePixNotFoundException e){
            return false;
        }

    }


    @Override
    public ChavePixExistsResponse chavePixExistsWithBody(String valorChave){
        var response = ChavePixExistsResponse.builder()
                .valorChave(valorChave)
                .build();
        try{
            var entity = getChavePixEntity(valorChave);
            response.setCodBanco(entity.getCodBanco());
            response.setTipoChave(entity.getTipoChave());
            response.setChaveExists(true);
            return response;
        } catch (ChavePixNotFoundException ex){
            return response;
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
