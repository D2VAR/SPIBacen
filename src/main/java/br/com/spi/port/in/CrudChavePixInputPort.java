package br.com.spi.port.in;

import br.com.spi.adapter.in.rest.controller.dto.ChavePixRequest;
import br.com.spi.domain.model.dto.ChavePixResponse;

public interface CrudChavePixInputPort{
    ChavePixResponse getChavePix(String valorChave);
    void saveChavePix(ChavePixRequest request);
    void deleteChavePix(String valorChave);
    void updateChavePix(ChavePixRequest request);
    boolean chavePixExists(String valorChave);
}
