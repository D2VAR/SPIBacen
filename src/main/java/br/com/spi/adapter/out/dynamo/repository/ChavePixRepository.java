package br.com.spi.adapter.out.dynamo.repository;

import br.com.spi.adapter.out.dynamo.entity.ChavePixEntity;
import br.com.spi.infrastructure.mapper.ChavePixMapper;
import br.com.spi.domain.model.ChavePix;
import br.com.spi.port.out.DatabaseOutputPort;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ChavePixRepository implements DatabaseOutputPort {

    private final ChavePixMapper mapper;
    private final DynamoDBMapper dynamoDBMapper;
    private final DynamoDBRepository repository;

    @Override
    public Optional<ChavePix> findChavePixByValor(String valorChave) {
        log.info("Busca chave pix por valor. Valor Chave: {}", valorChave);
        Optional<ChavePixEntity> chavePix = findChavePixByValorChave(valorChave);
        return chavePix.map(mapper::entityToModel);
    }

    private Optional<ChavePixEntity> findChavePixByValorChave(String valorChave) {
        DynamoDBQueryExpression<ChavePixEntity> queryExpression = new DynamoDBQueryExpression<>();

        queryExpression.setIndexName("ChavePix-index");
        queryExpression.setConsistentRead(false);
        queryExpression.setKeyConditionExpression("#valor_chave = :valor_chave");
        queryExpression.setExpressionAttributeNames(Map.of("#valor_chave", "valor_chave"));
        queryExpression.setExpressionAttributeValues(Map.of(":valor_chave", new AttributeValue(valorChave)));

        PaginatedQueryList<ChavePixEntity> result = dynamoDBMapper.query(ChavePixEntity.class, queryExpression);

        return result.stream().findFirst();
    }

    @Override
    public void salvarChavePix(ChavePix chavePix) {
        log.info("Persistencia de chave pix na base de dados no Bacen. Chave Pix: {}", chavePix);
        ChavePixEntity entity = mapper.modeltoEntity(chavePix);
        repository.save(entity);
    }
}
