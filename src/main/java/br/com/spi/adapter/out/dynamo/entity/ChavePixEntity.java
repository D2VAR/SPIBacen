package br.com.spi.adapter.out.dynamo.entity;

import br.com.spi.domain.model.TipoChave;
import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.Data;

@Data
@DynamoDBTable(tableName = "chave-pix")
public class ChavePixEntity {

    @DynamoDBHashKey(attributeName = "cod_banco")
    private Integer codBanco;

    @DynamoDBAttribute(attributeName = "numero_conta")
    private String numeroConta;

    @DynamoDBAttribute(attributeName = "agencia_conta")
    private Integer agenciaConta;

    @DynamoDBAttribute(attributeName = "cpf_cnpj")
    private String cpfCnpj;

    @DynamoDBAttribute(attributeName = "nome")
    private String nome;

    @DynamoDBTypeConvertedEnum
    @DynamoDBAttribute(attributeName = "tipo_chave")
    private TipoChave tipoChave;

    @DynamoDBIndexHashKey(attributeName = "valor_chave", globalSecondaryIndexName = "ChavePix-index")
    private String valorChave;
}

