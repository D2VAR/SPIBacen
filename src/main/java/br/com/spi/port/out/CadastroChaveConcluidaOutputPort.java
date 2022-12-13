package br.com.spi.port.out;

import br.com.spi.domain.model.ChavePix;


public interface CadastroChaveConcluidaOutputPort {
    void notificaCadastroConcluido(ChavePix novaChavePix);
}
