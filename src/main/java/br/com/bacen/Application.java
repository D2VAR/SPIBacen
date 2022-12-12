package br.com.bacen;

import lombok.extern.slf4j.Slf4j;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
@EnableDynamoDBRepositories
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        log.info("API BACEN RODANDO");
    }
}
