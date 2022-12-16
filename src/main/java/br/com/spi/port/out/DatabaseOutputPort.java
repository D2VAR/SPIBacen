package br.com.spi.port.out;

import br.com.spi.adapter.out.dynamo.entity.ChavePixDynamo;

import java.util.List;
import java.util.Optional;

public interface DatabaseOutputPort {
    void saveChavePix(ChavePixDynamo chavePix);
    void updateChavePix(ChavePixDynamo chavePix);
    void deleteChavePix(ChavePixDynamo chavePix);
    boolean chavePixExists(String valorChave);
    Optional<ChavePixDynamo> getChavePix(String valorChave);
    Optional<ChavePixDynamo> getChavePix(String codigoBanco,
                                         String cpfCnpj,
                                         String valorChave);
    List<ChavePixDynamo> getChavePixList(String codigoBanco, String cpfCnpj);
}
