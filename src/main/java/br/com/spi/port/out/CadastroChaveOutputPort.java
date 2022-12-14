package br.com.spi.port.out;

import br.com.spi.domain.model.ChavePix;


public interface CadastroChaveOutputPort {
    void notificaCadastroConcluido(ChavePix novaChavePix);

    void notificaErroCadastro(ChavePix novaChavePix);
}
