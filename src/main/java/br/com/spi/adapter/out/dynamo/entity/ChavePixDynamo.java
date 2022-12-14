package br.com.spi.adapter.out.dynamo.entity;

import br.com.spi.domain.model.TipoChave;
import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamoDBTable(tableName = "chaves-pix")
public class ChavePixDynamo {
    private String codBanco;
    private String cpfCnpjValorChave;
    private String numeroConta;
    private String agenciaConta;
    private String cpfCnpj;
    private String nome;
    private TipoChave tipoChave;
    private String valorChave;

    @DynamoDBHashKey(attributeName = "codigo_banco")
    public String getCodBanco() {
        return codBanco;
    }

    public void setCodBanco(String codBanco) {
        this.codBanco = codBanco;
    }

    @DynamoDBRangeKey(attributeName = "cpf_cnpj_valor_chave")
    public String getCpfCnpjValorChave() {
        return cpfCnpjValorChave;
    }
    public void setCpfCnpjValorChave(String cpfCnpjValorChave) {
        this.cpfCnpjValorChave = cpfCnpjValorChave;
    }

    @DynamoDBAttribute(attributeName = "numero_conta")
    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }

    @DynamoDBAttribute(attributeName = "agencia_conta")
    public String getAgenciaConta() {
        return agenciaConta;
    }

    public void setAgenciaConta(String agenciaConta) {
        this.agenciaConta = agenciaConta;
    }

    @DynamoDBAttribute(attributeName = "cpf_cnpj")
    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    @DynamoDBAttribute(attributeName = "nome")
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @DynamoDBTypeConvertedEnum
    @DynamoDBAttribute(attributeName = "tipo_chave")
    public TipoChave getTipoChave() {
        return tipoChave;
    }

    public void setTipoChave(TipoChave tipoChave) {
        this.tipoChave = tipoChave;
    }

    @DynamoDBIndexHashKey(attributeName = "valor_chave", globalSecondaryIndexName = "ChavePix-index")
    @DynamoDBAttribute(attributeName = "valor_chave")
    public String getValorChave() {
        return valorChave;
    }

    public void setValorChave(String valorChave) {
        this.valorChave = valorChave;
    }
}

