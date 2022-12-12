package br.com.bacen.port.out;

import br.com.bacen.domain.model.ChavePix;

import java.util.Optional;

public interface DatabaseOutputPort {
    Optional<ChavePix> findChavePixByValor(String valor_chave);
}
