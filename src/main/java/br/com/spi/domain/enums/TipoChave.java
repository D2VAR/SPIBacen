package br.com.spi.domain.enums;

import lombok.Getter;

@Getter
public enum TipoChave {
    CPF("CPF"),
    EMAIL("EMAIL"),
    CELULAR("CELULAR"),
    ALEATORIA("ALEATORIA");

    private final String descricaoTipo;


    TipoChave(String descricaoTipo) {
        this.descricaoTipo = descricaoTipo;
    }
}
