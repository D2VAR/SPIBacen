package br.com.spi.domain.service;

import br.com.spi.domain.dto.ChavePixDTO;
import br.com.spi.domain.model.ChavePix;
import br.com.spi.infrastructure.mapper.ChavePixMapper;
import br.com.spi.port.in.CadastroChavePixInputPort;
import br.com.spi.port.out.CadastroChaveConcluidaOutputPort;
import br.com.spi.port.out.CadastroChaveInvalidoOutputPort;
import br.com.spi.port.out.DatabaseOutputPort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CadastroChavePixService implements CadastroChavePixInputPort {

    private final ChavePixMapper mapper;
    private final DatabaseOutputPort databaseOutputPort;
    private final CadastroChaveConcluidaOutputPort cadastroChaveConcluidaOutputPort;
    private final CadastroChaveInvalidoOutputPort cadastroChaveInvalidoOutputPort;

    public CadastroChavePixService(DatabaseOutputPort databaseOutpurPort, CadastroChaveConcluidaOutputPort cadastroChaveConcluidaOutputPort, CadastroChaveInvalidoOutputPort cadastroChaveInvalidoOutputPort, ChavePixMapper mapper) {
        this.databaseOutputPort = databaseOutpurPort;
        this.cadastroChaveConcluidaOutputPort = cadastroChaveConcluidaOutputPort;
        this.cadastroChaveInvalidoOutputPort = cadastroChaveInvalidoOutputPort;
        this.mapper = mapper;
    }
    @Override
    public void cadastrarChave(ChavePixDTO chavePixDTO) {
        Optional<ChavePix> chavePix = databaseOutputPort.findChavePixByValor(chavePixDTO.getValorChave());

        if (chavePix.isPresent()){
            cadastroChaveInvalidoOutputPort.notificaErroCadastro(chavePix);
        }

        ChavePix novaChavePix = mapper.dtoToModel(chavePixDTO);
        databaseOutputPort.salvarChavePix(novaChavePix);
        cadastroChaveConcluidaOutputPort.notificaCadastroConcluido(novaChavePix);
    }
}
