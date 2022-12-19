package br.com.spi.infrastructure.dto.transacao;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TransacaoValidadaResponse {
    @JsonProperty("transaction_id")
    private String transactionId;
    @JsonProperty("pix_realizado")
    private Boolean pixRealizado;
    @JsonProperty("nome")
    private String nome;
    @JsonProperty("cpf_cnpj")
    private String cpfCnpj;
    @JsonProperty("chave_destino")
    private String chaveDestino;
    @JsonProperty("valor_transferencia")
    private BigDecimal valor;
    @JsonProperty("codigo_banco_destino")
    private String codBancoDestino;
}
