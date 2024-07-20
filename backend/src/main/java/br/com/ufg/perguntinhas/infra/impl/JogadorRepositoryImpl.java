package br.com.ufg.perguntinhas.infra.impl;

import br.com.ufg.perguntinhas.infra.JogadorRepository;
import br.com.ufg.perguntinhas.infra.data.repository.JogadorDataRepository;
import br.com.ufg.perguntinhas.records.JogadorRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
@Transactional
@RequiredArgsConstructor
public class JogadorRepositoryImpl implements JogadorRepository {

    private final JogadorDataRepository jogadorDataRepository;

    @Override
    public UUID save(JogadorRecord jogador) {
        return jogadorDataRepository.save(jogador.toEntity()).getUuid();
    }
}
