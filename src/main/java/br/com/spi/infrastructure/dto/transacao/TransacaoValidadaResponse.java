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
public class TransacaoValidadaResponse {
    @JsonProperty("transaction_id")
    private String transactionId;
    @JsonProperty("pix_realizado")
    private boolean pixRealizado;
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

    public TransacaoValidadaResponse(PixTransferResponse response){
        this.transactionId = response.getTransactionId();
        this.nome = response.getNome();
        this.cpfCnpj = response.getCpfCnpj();
        this.tipoChave = response.getTipoChave();
        this.chaveDestino = response.getChaveDestino();
        this.valor = response.getValor();
        this.codBancoOrigem = response.getCodBancoOrigem();
    }
}
