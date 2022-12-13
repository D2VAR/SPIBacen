package br.com.spi.port.out;

import br.com.spi.domain.model.ChavePix;

import java.util.Optional;

public interface CadastroChaveInvalidoOutputPort {
    void notificaErroCadastro(Optional<ChavePix> chavePix);
}
