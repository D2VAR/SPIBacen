package br.com.spi.port.in;

import br.com.spi.infrastructure.dto.transacao.TransacaoValidadaRequest;

public interface ValidacaoTransacaoInputPort {
    void retornarValidacao(TransacaoValidadaRequest request);
}
