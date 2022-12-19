package br.com.spi.port.out;

import br.com.spi.infrastructure.dto.transacao.TransacaoValidadaResponse;

public interface ValidacaoTransacaoOutputPort {
    void notificaFalha(TransacaoValidadaResponse response);

    void notificaSucesso(TransacaoValidadaResponse response);
}
