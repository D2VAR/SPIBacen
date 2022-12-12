package br.com.bacen.adapter.out.dynamo.repository;

import br.com.bacen.adapter.out.dynamo.entity.ChavePixEntity;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ChavePixRepository {

    private final DynamoDBMapper dynamoDBMapper;


    public Optional<ChavePixEntity> findChavePixByValorChave(String valor_chave) {
        DynamoDBQueryExpression<ChavePixEntity> queryExpression = new DynamoDBQueryExpression<>();

        queryExpression.setIndexName("ChavePix-index");
        queryExpression.setConsistentRead(false);
        queryExpression.setKeyConditionExpression("#valor_chave = :valor_chave");
        queryExpression.setExpressionAttributeNames(Map.of("#valor_chave", "valor_chave"));
        queryExpression.setExpressionAttributeValues(Map.of(":valor_chave", new AttributeValue(valor_chave)));

        PaginatedQueryList<ChavePixEntity> result = dynamoDBMapper.query(ChavePixEntity.class, queryExpression);

        return result.stream().findFirst();
    }
}
