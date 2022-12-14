package br.com.spi.domain.model.dto;

import br.com.spi.domain.model.TipoChave;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChavePixResponse{
    @JsonProperty("codigo_banco")
    private String codBanco;
    @JsonProperty("cpf_cnpj_valor_chave")
    private String cpgCnpjValorChave;
    @JsonProperty("numero_conta")
    private String numeroConta;
    @JsonProperty("agencia_conta")
    private String agenciaConta;
    @JsonProperty("cpf_cnpj")
    private String cpfCnpj;
    @JsonProperty("nome")
    private String nome;
    @JsonProperty("tipo_chave")
    private TipoChave tipoChave;
    @JsonProperty("valor_chave")
    private String valorChave;

}
