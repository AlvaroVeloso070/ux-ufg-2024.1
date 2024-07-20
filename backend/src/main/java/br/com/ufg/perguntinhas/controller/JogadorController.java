package br.com.ufg.perguntinhas.controller;

import br.com.ufg.perguntinhas.application.JogadorService;
import br.com.ufg.perguntinhas.records.JogadorRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/jogador")
public class JogadorController {

    private final JogadorService jogadorService;

    @PostMapping
    public ResponseEntity<JogadorRecord> criarJogador(@RequestBody JogadorRecord jogador) {
        var uuid = jogadorService.criarJogador(jogador);

        if (uuid != null) {
            return ResponseEntity.ok(new JogadorRecord(jogador.nome(), uuid.toString()));
        }

        return ResponseEntity.badRequest().build();
    }
}
