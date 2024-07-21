package br.com.ufg.perguntinhas.infra;

import br.com.ufg.perguntinhas.infra.data.Pergunta;
import br.com.ufg.perguntinhas.records.PerguntaRecord;

import java.util.UUID;

public interface PerguntaRepository {
    Pergunta save(PerguntaRecord pergunta);

    PerguntaRecord getPerguntaAleatoriaJogador(String uuidJogador);

    UUID getPerguntaPorAlternativa(String uuidAlternativa);
}
