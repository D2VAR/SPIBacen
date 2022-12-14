package br.com.spi.infrastructure.dto.transacao;

import br.com.spi.infrastructure.enums.TipoChave;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PixTransferRequest{
    @JsonProperty("transaction_id")
    private String transactionId;
    @JsonProperty("nome")
    private String nome;
    @JsonProperty("cpf_cnpj")
    private String cpfCnpj;
    @JsonProperty("tipo_chave")
    private TipoChave tipoChave;
    @JsonProperty("chave_destino")
    private String chaveDestino;
    @JsonProperty("valor_transferencia")
    private BigDecimal valor;
    @JsonProperty("codigo_banco_origem")
    private String codBancoOrigem;
}
