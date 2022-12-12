package br.com.spi.port.out;

import br.com.spi.domain.model.ChavePix;

import java.util.Optional;

public interface DatabaseOutputPort {
    Optional<ChavePix> findChavePixByValor(String valorChave);
}
