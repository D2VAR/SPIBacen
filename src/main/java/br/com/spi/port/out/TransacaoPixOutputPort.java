package br.com.spi.port.out;

import br.com.spi.infrastructure.dto.transacao.TransacaoPixResponse;

public interface TransacaoPixOutputPort {
    void enviarPix(TransacaoPixResponse response);

}
