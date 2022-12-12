package br.com.bacen.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChavePixResponse {
    private Integer cod_banco;
    private String numero_conta;
    private Integer agencia_conta;
    private String cpf_cnpj;
    private String nome;
    private TipoChave tipo_chave;
    private String valor_chave;

    public enum TipoChave{
        CPF,
        EMAIL,
        CELULAR,
        ALEATORIA
    }
}
