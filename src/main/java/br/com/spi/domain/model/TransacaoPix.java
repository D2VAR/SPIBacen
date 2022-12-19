package br.com.spi.domain.model;

import br.com.spi.infrastructure.enums.TipoChave;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransacaoPix {
    private String transactionId;
    private String nome;
    private String cpfCnpj;
    private TipoChave tipoChave;
    private String chaveDestino;
    private BigDecimal valor;
    private String codBancoOrigem;
}
