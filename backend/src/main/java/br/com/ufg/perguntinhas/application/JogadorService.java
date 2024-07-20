package br.com.ufg.perguntinhas.application;

import br.com.ufg.perguntinhas.infra.JogadorRepository;
import br.com.ufg.perguntinhas.records.JogadorRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JogadorService {

    private final JogadorRepository jogadorRepository;

    public UUID criarJogador(JogadorRecord jogador) {
        return jogadorRepository.save(jogador);
    }
}