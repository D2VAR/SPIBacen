package br.com.spi.port.out;

import br.com.spi.infrastructure.dto.transacao.PixTransferResponse;

public interface PixTransferValidation{
    void enviarPix(PixTransferResponse response);

}
