package br.com.spi.domain.service;

import br.com.spi.domain.dto.ChavePixDTO;
import br.com.spi.domain.model.ChavePix;
import br.com.spi.infrastructure.mapper.ChavePixMapper;
import br.com.spi.port.in.CadastroChavePixInputPort;
import br.com.spi.port.out.CadastroChaveOutputPort;
import br.com.spi.port.out.DatabaseOutputPort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CadastroChavePixService implements CadastroChavePixInputPort {

    private final ChavePixMapper mapper;
    private final DatabaseOutputPort databaseOutputPort;
    private final CadastroChaveOutputPort cadastroOutputPort;

    public CadastroChavePixService(DatabaseOutputPort databaseOutputPort, ChavePixMapper mapper, CadastroChaveOutputPort cadastroOutputPort) {
        this.databaseOutputPort = databaseOutputPort;
        this.mapper = mapper;
        this.cadastroOutputPort = cadastroOutputPort;
    }
    @Override
    public void cadastrarChave(ChavePixDTO chavePixDTO) {
        Optional<ChavePix> chavePix = databaseOutputPort.findChavePixByValor(chavePixDTO.getValorChave());

        if (chavePix.isPresent()){
            cadastroOutputPort.notificaErroCadastro(chavePix.get());
        }

        ChavePix novaChavePix = mapper.dtoToModel(chavePixDTO);
        databaseOutputPort.salvarChavePix(novaChavePix);
        cadastroOutputPort.notificaCadastroConcluido(novaChavePix);
    }
}
