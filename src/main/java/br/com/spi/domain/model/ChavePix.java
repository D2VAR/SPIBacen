package br.com.spi.domain.model;


import br.com.spi.infrastructure.enums.TipoChave;
import lombok.Getter;
import lombok.Setter;
import static br.com.spi.infrastructure.validator.Validator.validateObject;
import static br.com.spi.infrastructure.validator.Validator.validateString;

@Getter
@Setter
public class ChavePix{
    private String transactionId;
    private String codBanco;
    private String cpfCnpjValorChave;
    private String numeroConta;
    private String agenciaConta;
    private String cpfCnpj;
    private String nome;
    private TipoChave tipoChave;
    private String valorChave;

    public ChavePix(String transactionId, String codBanco, String cpfCnpjValorChave, String numeroConta,
                    String agenciaConta, String cpfCnpj, String nome, TipoChave tipoChave, String valorChave){
        setTransactionId(transactionId);
        setCodBanco(codBanco);
        setCpfCnpjValorChave(cpfCnpjValorChave);
        setNumeroConta(numeroConta);
        setAgenciaConta(agenciaConta);
        setCpfCnpj(cpfCnpj);
        setNome(nome);
        setTipoChave(tipoChave);
        setValorChave(valorChave);
    }

    public ChavePix(String transactionId, String codBanco, String numeroConta, String agenciaConta,
                    String cpfCnpj, String nome, TipoChave tipoChave, String valorChave){
        setTransactionId(transactionId);
        setCodBanco(codBanco);
        setCpfCnpjValorChave(cpfCnpj, valorChave);
        setNumeroConta(numeroConta);
        setAgenciaConta(agenciaConta);
        setCpfCnpj(cpfCnpj);
        setNome(nome);
        setTipoChave(tipoChave);
        setValorChave(valorChave);
    }


    public void setTransactionId(String transactionId){
        this.transactionId = transactionId;
    }

    public void setCodBanco(String codBanco){
        validateString(codBanco);
        this.codBanco = codBanco;
    }

    public void setCpfCnpjValorChave(String cpfCnpjValorChave){
        validateString(cpfCnpjValorChave);
        this.cpfCnpjValorChave = cpfCnpjValorChave;
    }

    public void setCpfCnpjValorChave(String cpfCnpj, String valorChave){
        validateString(cpfCnpj);
        validateString(valorChave);
        this.cpfCnpjValorChave = cpfCnpj+"#"+valorChave;
    }

    public void setNumeroConta(String numeroConta){
        validateString(numeroConta);
        this.numeroConta = numeroConta;
    }

    public void setAgenciaConta(String agenciaConta){
        validateString(agenciaConta);
        this.agenciaConta = agenciaConta;
    }

    public void setCpfCnpj(String cpfCnpj){
        validateString(cpfCnpj);
        this.cpfCnpj = cpfCnpj;
    }

    public void setNome(String nome){
        validateString(nome);
        this.nome = nome;
    }

    public void setTipoChave(TipoChave tipoChave){
        validateObject(tipoChave);
        this.tipoChave = tipoChave;
    }

    public void setValorChave(String valorChave){
        validateString(valorChave);
        this.valorChave = valorChave;
    }


}
