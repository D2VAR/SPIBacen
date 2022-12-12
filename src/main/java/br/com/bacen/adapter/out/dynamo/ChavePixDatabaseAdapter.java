package br.com.bacen.adapter.out.dynamo;

import br.com.bacen.adapter.out.dynamo.entity.ChavePixEntity;
import br.com.bacen.adapter.out.dynamo.repository.ChavePixRepository;
import br.com.bacen.domain.mapper.ChavePixMapper;
import br.com.bacen.domain.model.ChavePix;
import br.com.bacen.port.out.DatabaseOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ChavePixDatabaseAdapter implements DatabaseOutputPort {

    private final ChavePixRepository repository;
    private final ChavePixMapper mapper;

    @Override
    public Optional<ChavePix> findChavePixByValor(String valor_chave) {
        Optional<ChavePixEntity> chavePix = repository.findChavePixByValorChave(valor_chave);
        return chavePix.map(mapper::modelToDomain);
    }
}
