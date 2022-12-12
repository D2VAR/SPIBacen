package br.com.bacen.domain.service;

import br.com.bacen.adapter.exception.ChavePixNaoEncontradaException;
import br.com.bacen.domain.dto.ChavePixResponse;
import br.com.bacen.domain.mapper.ChavePixMapper;
import br.com.bacen.domain.model.ChavePix;
import br.com.bacen.port.in.VerificacaoChavePixInputPort;
import br.com.bacen.port.out.DatabaseOutputPort;
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
                .map(mapper::domainToResponse)
                .orElseThrow(ChavePixNaoEncontradaException::new);
    }
}
