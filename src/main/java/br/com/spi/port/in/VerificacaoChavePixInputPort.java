package br.com.spi.port.in;

import br.com.spi.domain.dto.ChavePixResponse;

public interface VerificacaoChavePixInputPort {

    ChavePixResponse findChavePixByValor(String valorChave);
}
