package br.com.spi.port.in;

import br.com.spi.infrastructure.dto.chave.ChavePixExistsResponse;
import br.com.spi.infrastructure.dto.chave.ChavePixRequest;
import br.com.spi.infrastructure.dto.chave.ChavePixResponse;

public interface ChavePixInput{
    ChavePixResponse getChavePix(String valorChave);
    void saveChavePix(ChavePixRequest request);
    void deleteChavePix(String valorChave);
    void updateChavePix(ChavePixRequest request);
    boolean chavePixExists(String valorChave);
    ChavePixExistsResponse chavePixExistsWithBody(String valorChave);

}
