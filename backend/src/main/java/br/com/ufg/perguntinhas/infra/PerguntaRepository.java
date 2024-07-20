package br.com.ufg.perguntinhas.infra;

import br.com.ufg.perguntinhas.infra.data.Pergunta;
import br.com.ufg.perguntinhas.records.PerguntaRecord;

public interface PerguntaRepository {
    Pergunta save(PerguntaRecord pergunta);

    PerguntaRecord getPerguntaAleatoriaJogador(String uuidJogador);
}
