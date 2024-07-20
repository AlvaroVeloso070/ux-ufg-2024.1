package br.com.ufg.perguntinhas.enums;

public enum Dificuldade {
    FACIL(1L),
    MEDIO(2L),
    DIFICIL(3L),
    MUITO_DIFICIL(4L);

    private long multiplicador;

    Dificuldade(long multiplicador) {
        this.multiplicador = multiplicador;
    }
}
