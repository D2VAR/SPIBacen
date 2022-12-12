package br.com.spi.adapter.out;

import br.com.spi.adapter.out.dynamo.entity.ChavePixEntity;
import br.com.spi.adapter.out.dynamo.repository.ChavePixRepository;
import br.com.spi.domain.mapper.ChavePixMapper;
import br.com.spi.domain.model.ChavePix;
import br.com.spi.port.out.DatabaseOutputPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class ChavePixDatabaseAdapter implements DatabaseOutputPort {

    private final ChavePixRepository repository;
    private final ChavePixMapper mapper;

    @Override
    public Optional<ChavePix> findChavePixByValor(String valorChave) {
        log.info("Busca chave pix por valor. Valor Chave: {}", valorChave);
        Optional<ChavePixEntity> chavePix = repository.findChavePixByValorChave(valorChave);
        return chavePix.map(mapper::entityToModel);
    }
}
