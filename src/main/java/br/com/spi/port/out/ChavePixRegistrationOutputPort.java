package br.com.spi.port.out;

import br.com.spi.infrastructure.dto.ChavePixResponse;

public interface ChavePixRegistrationOutputPort{
    void notifySuccessfulRegistration(ChavePixResponse response);
    void notifyRegistrationFailure(ChavePixResponse response);
}
