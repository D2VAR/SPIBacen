package br.com.spi.adapter.out.dynamo.repository;

import br.com.spi.adapter.out.dynamo.entity.ChavePixDynamo;
import br.com.spi.exception.ChavePixDuplicateException;
import br.com.spi.exception.ChavePixNotFoundException;
import br.com.spi.port.out.DatabaseAccess;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ChavePixRepository implements DatabaseAccess{
    private final DynamoDBMapper dynamoDBMapper;
    @Value("${dynamodb.index.chave-pix}")
    private String chavePixIndex;

    @Override
    public void updateChavePix(ChavePixDynamo chavePix){
        deleteChavePix(chavePix);
        saveChavePix(chavePix);
    }

    @Override
    public void saveChavePix(ChavePixDynamo chavePix){
        if (chavePixExists(chavePix.getValorChave()))
            throw new ChavePixDuplicateException("Chave Pix already exists!");
        dynamoDBMapper.save(chavePix);
    }

    @Override
    public boolean chavePixExists(String valorChave){
        var chavePix = getChavePix(valorChave);
        return chavePix.isPresent();
    }

    @Override
    public void deleteChavePix(ChavePixDynamo chavePix){
        if (chavePixExists(chavePix.getValorChave())){
            dynamoDBMapper.delete(chavePix);
            return;
        }
        throw new ChavePixNotFoundException("Chave Pix does not exists!");
    }

    @Override
    public Optional<ChavePixDynamo> getChavePix(String valorChave){
        var expNames = Map.of("#k1", "valor_chave");
        var expValues = Map.of(":v1", new AttributeValue().withS(valorChave));

        var queryExpression = getIndexQueryExpression(
                expNames,
                expValues,
                chavePixIndex
        );

        List<ChavePixDynamo> result = dynamoDBMapper.query(ChavePixDynamo.class, queryExpression);

        return result.stream().findFirst();
    }

    private DynamoDBQueryExpression<ChavePixDynamo> getIndexQueryExpression(Map<String, String> expNames,
                                                                            Map<String, AttributeValue> expValues,
                                                                            String indexName){
        var exp = getDefaultQueryExpression(expNames, expValues);
        setIndexOnQueryExpression(exp, indexName);
        return exp;
    }

    private DynamoDBQueryExpression<ChavePixDynamo> getDefaultQueryExpression(Map<String, String> expNames, Map<String, AttributeValue> expValues){
        return new DynamoDBQueryExpression<ChavePixDynamo>()
                .withExpressionAttributeNames(expNames)
                .withExpressionAttributeValues(expValues)
                .withKeyConditionExpression("#k1 = :v1 and begins_with(#k2, :v2)");
    }

    private void setIndexOnQueryExpression(DynamoDBQueryExpression<ChavePixDynamo> exp, String indexName){
        exp.setIndexName(indexName);
        exp.setConsistentRead(false);
        exp.setKeyConditionExpression("#k1 = :v1");
    }

    @Override
    public Optional<ChavePixDynamo> getChavePix(String codigoBanco, String cpfCnpj, String valorChave){
        var rangeKey = cpfCnpj + "#" + valorChave;
        var expNames = Map.of("#k1", "codigo_banco", "#k2", "cpf_cnpj_valor_chave");
        var expValues = Map.of(":v1", new AttributeValue().withS(codigoBanco), ":v2", new AttributeValue().withS(rangeKey));

        var queryExpression = getDefaultQueryExpression(
                expNames,
                expValues
        );

        List<ChavePixDynamo> result = dynamoDBMapper.query(ChavePixDynamo.class, queryExpression);

        return result.stream().findFirst();
    }

    @Override
    public List<ChavePixDynamo> getChavePixList(String codigoBanco, String cpfCnpj){
        var expNames = Map.of("#k1", "codigo_banco", "#k2", "cpf_cnpj_valor_chave");
        var expValues = Map.of(":v1", new AttributeValue().withS(codigoBanco), ":v2", new AttributeValue().withS(codigoBanco));
        var queryExpression = getDefaultQueryExpression(
                expNames,
                expValues
        );
        return dynamoDBMapper.query(ChavePixDynamo.class, queryExpression);
    }
}
