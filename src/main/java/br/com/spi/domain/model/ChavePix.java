package br.com.spi.domain.model;

import br.com.spi.domain.enums.TipoChave;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChavePix {
    private Integer codBanco;
    private String numeroConta;
    private Integer agenciaConta;
    private String cpfCnpj;
    private String nome;
//    private TipoChave tipoChave;
    private String valorChave;
}
