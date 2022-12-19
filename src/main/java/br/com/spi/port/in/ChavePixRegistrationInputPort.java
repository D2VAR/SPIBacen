package br.com.spi.port.in;

import br.com.spi.infrastructure.dto.chave.ChavePixRequest;

public interface ChavePixRegistrationInputPort{
    void registerChavePix(ChavePixRequest request);
}
