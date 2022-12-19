package br.com.spi.port.in;

import br.com.spi.infrastructure.dto.transacao.TransacaoPixRequest;

public interface TransacaoPixInputPort {
    void enviarTransacaoPix(TransacaoPixRequest request);
}
