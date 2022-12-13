package br.com.spi.adapter.out.dynamo.repository;

import br.com.spi.adapter.out.dynamo.entity.ChavePixEntity;
import org.socialsignin.spring.data.dynamodb.repository.DynamoDBCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DynamoDBRepository extends DynamoDBCrudRepository<ChavePixEntity, String> {
}
