package br.com.spi.adapter.out.dynamo.config;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DynamoDBConfig {

    @Value("${aws.access.key.id}")
    private String awsAccessKeyId;

    @Value("${aws.access.key.secret}")
    private String awsAcessKeySecret;

    @Value("${dynamodb.service.endpoint}")
    private String dynamoDBServiceEndPoint;

    @Value("${dynamodb.service.region}")
    private String dynamoDBRegion;

    @Bean
    @Primary
    public DynamoDBMapper dynamoDBMapper(AmazonDynamoDB amazonDynamoDB) {
        return new DynamoDBMapper(amazonDynamoDB);
    }

    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        return AmazonDynamoDBClientBuilder
                .standard()
                .withEndpointConfiguration(endPointConfiguration())
                .withCredentials(credentialsProvider())
                .build();
    }

    private AWSCredentialsProvider credentialsProvider() {
        return new AWSStaticCredentialsProvider(
                new BasicAWSCredentials(awsAccessKeyId, awsAcessKeySecret)
        );
    }

    private AwsClientBuilder.EndpointConfiguration endPointConfiguration() {
        return new AwsClientBuilder.EndpointConfiguration(dynamoDBServiceEndPoint, dynamoDBRegion);
    }
}
