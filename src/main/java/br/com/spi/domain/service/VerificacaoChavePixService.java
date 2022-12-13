package br.com.spi.domain.service;

import br.com.spi.adapter.exception.ChavePixNaoEncontradaException;
import br.com.spi.domain.dto.ChavePixResponse;
import br.com.spi.domain.mapper.ChavePixMapper;
import br.com.spi.domain.model.ChavePix;
import br.com.spi.port.in.VerificacaoChavePixInputPort;
import br.com.spi.port.out.DatabaseOutputPort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VerificacaoChavePixService implements VerificacaoChavePixInputPort {

    private final DatabaseOutputPort databaseOutputPort;
    private final ChavePixMapper mapper;

    public VerificacaoChavePixService(DatabaseOutputPort databaseOutpurPort, ChavePixMapper mapper) {
        this.databaseOutputPort = databaseOutpurPort;
        this.mapper = mapper;
    }

    @Override
    public ChavePixResponse findChavePixByValor(String valorChave) {
        Optional<ChavePix> chavePix = databaseOutputPort.findChavePixByValor(valorChave);

        return chavePix
                .map(mapper::modelToResponse)
                .orElseThrow(ChavePixNaoEncontradaException::new);
    }
}