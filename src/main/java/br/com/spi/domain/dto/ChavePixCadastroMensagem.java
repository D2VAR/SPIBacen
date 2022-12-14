package br.com.spi.domain.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ChavePixCadastroMensagem {

    private String id;
    private String status;
    private String codBanco;
    private String numeroConta;
    private String agenciaConta;
    private String cpfCnpj;
    private String nome;
//    private String tipoChave;
    private String valorChave;
}
