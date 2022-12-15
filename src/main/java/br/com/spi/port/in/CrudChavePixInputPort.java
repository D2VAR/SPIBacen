package br.com.spi.port.in;

import br.com.spi.infrastructure.dto.ChavePixRequest;
import br.com.spi.infrastructure.dto.ChavePixResponse;

public interface CrudChavePixInputPort{
    ChavePixResponse getChavePix(String valorChave);
    void saveChavePix(ChavePixRequest request);
    void deleteChavePix(String valorChave);
    void updateChavePix(ChavePixRequest request);
    boolean chavePixExists(String valorChave);
}
