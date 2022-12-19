package br.com.spi.infrastructure.dto.chave;

import br.com.spi.infrastructure.enums.TipoChave;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChavePixExistsResponse{
    @JsonProperty("codigo_banco")
    private String codBanco;
    @JsonProperty("chave_existente")
    private Boolean chaveExists;
    @JsonProperty("tipo_chave")
    private TipoChave tipoChave;
    @JsonProperty("valor_chave")
    private String valorChave;
}

