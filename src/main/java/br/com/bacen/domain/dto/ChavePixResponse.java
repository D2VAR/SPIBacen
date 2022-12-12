package br.com.bacen.domain.dto;

import br.com.bacen.domain.model.TipoChave;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChavePixResponse {
    private Integer codBanco;
    private String numeroConta;
    private Integer agenciaConta;
    private String cpfCnpj;
    private String nome;
    private TipoChave tipoChave;
    private String valorChave;
}
