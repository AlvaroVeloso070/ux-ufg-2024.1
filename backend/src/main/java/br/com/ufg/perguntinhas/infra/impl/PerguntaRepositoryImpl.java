package br.com.ufg.perguntinhas.infra.impl;

import br.com.ufg.perguntinhas.infra.PerguntaRepository;
import br.com.ufg.perguntinhas.infra.data.Pergunta;
import br.com.ufg.perguntinhas.infra.data.repository.PerguntaDataRepository;
import br.com.ufg.perguntinhas.records.PerguntaRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
@RequiredArgsConstructor
public class PerguntaRepositoryImpl implements PerguntaRepository {

    private final PerguntaDataRepository perguntaDataRepository;

    @Override
    public Pergunta save(PerguntaRecord pergunta) {
        return perguntaDataRepository.save(pergunta.toEntity());
    }
}
