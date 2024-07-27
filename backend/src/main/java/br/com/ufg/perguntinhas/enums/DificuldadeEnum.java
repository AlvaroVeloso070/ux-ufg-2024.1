package br.com.ufg.perguntinhas.enums;

import lombok.Getter;

@Getter
public enum DificuldadeEnum {
    FACIL(1L),
    MEDIO(2L),
    DIFICIL(3L),
    MUITO_DIFICIL(4L);

    private long multiplicador;

    DificuldadeEnum(long multiplicador) {
        this.multiplicador = multiplicador;
    }
}
