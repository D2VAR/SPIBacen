package br.com.spi.domain.dto;

import br.com.spi.domain.enums.StatusChavePix;
import br.com.spi.domain.enums.TipoChave;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChavePixResponse {
    private StatusChavePix status;
    private Integer codBanco;
    private String numeroConta;
    private Integer agenciaConta;
    private String cpfCnpj;
    private String nome;
//    private TipoChave tipoChave;
    private String valorChave;

    public ChavePixResponse(String valorChave, StatusChavePix status) {
        this.valorChave = valorChave;
        this.status = status;
    }
}

