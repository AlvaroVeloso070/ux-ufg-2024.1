package br.com.ufg.perguntinhas.infra;

import br.com.ufg.perguntinhas.records.RespostaRecord;

import java.util.UUID;

public interface RespostaRepository {
    void incluirResposta(RespostaRecord resposta, UUID uuidPergunta);
}
