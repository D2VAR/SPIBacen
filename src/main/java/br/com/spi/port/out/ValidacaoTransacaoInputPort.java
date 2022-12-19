package br.com.spi.port.out;

import br.com.spi.infrastructure.dto.transacao.TransacaoValidadaRequest;

public interface ValidacaoTransacaoInputPort {

    void retornarSucesso(TransacaoValidadaRequest request);
    void retornarFalha(TransacaoValidadaRequest request);
}
