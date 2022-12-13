package br.com.spi.domain.model.dto;

import br.com.spi.domain.model.TipoChave;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChavePixDto {
    private Integer codBanco;
    private String cpgCnpjValorChave;
    private String numeroConta;
    private Integer agenciaConta;
    private String cpfCnpj;
    private String nome;
    private TipoChave tipoChave;
    private String valorChave;

}
