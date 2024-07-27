package br.com.ufg.perguntinhas.infra.impl;

import br.com.ufg.perguntinhas.infra.RespostaRepository;
import br.com.ufg.perguntinhas.infra.data.Alternativa;
import br.com.ufg.perguntinhas.infra.data.Jogador;
import br.com.ufg.perguntinhas.infra.data.Pergunta;
import br.com.ufg.perguntinhas.infra.data.Resposta;
import br.com.ufg.perguntinhas.infra.data.repository.JogadorDataRepository;
import br.com.ufg.perguntinhas.infra.data.repository.RespostaDataRepository;
import br.com.ufg.perguntinhas.records.RespostaRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class RespostaRepositoryImpl implements RespostaRepository {

    private final RespostaDataRepository respostaDataRepository;
    private final JogadorDataRepository jogadorDataRepository;

    @Override
    public void incluirResposta(RespostaRecord respostaRecord, UUID uuidPergunta) {
        Resposta resposta = Resposta.builder()
                .alternativaRespondida(getAlternativaRespondida(respostaRecord.uuidAlternativa()))
                .pergunta(getPergunta(uuidPergunta))
                .jogador(getJogador(respostaRecord))
                .tempoResposta(respostaRecord.tempoResposta())
                .build();

        respostaDataRepository.save(resposta);
    }

    private Alternativa getAlternativaRespondida(String uuidAlternativa) {
        return Alternativa.builder().uuid(UUID.fromString(uuidAlternativa)).build();
    }

    private Pergunta getPergunta(UUID uuidPergunta) {
        return Pergunta.builder().uuid(uuidPergunta).build();
    }

    private Jogador getJogador(RespostaRecord respostaRecord) {
        return jogadorDataRepository.findById(UUID.fromString(respostaRecord.uuidJogador())).orElse(null);
    }
}
