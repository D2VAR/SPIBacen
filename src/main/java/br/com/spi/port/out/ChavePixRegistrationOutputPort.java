package br.com.spi.port.out;

import br.com.spi.infrastructure.dto.chave.ChavePixResponse;

public interface ChavePixRegistrationOutputPort{
    void notifySuccessfulRegistration(ChavePixResponse response);
    void notifyRegistrationFailure(ChavePixResponse response);
}
