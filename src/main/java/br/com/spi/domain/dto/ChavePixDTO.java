package br.com.spi.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChavePixDTO {

    private String codBanco;
    private String numeroConta;
    private String agenciaConta;
    private String cpfCnpj;
    private String nome;
//    private String tipoChave;
    private String valorChave;
}
