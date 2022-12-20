package br.com.spi.port.in;

import br.com.spi.infrastructure.dto.transacao.PixTransferRequest;

public interface PixTransferReceive{
    void validatePix(PixTransferRequest request);
}
