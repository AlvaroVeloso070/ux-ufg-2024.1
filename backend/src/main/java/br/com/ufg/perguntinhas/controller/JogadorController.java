package br.com.ufg.perguntinhas.controller;

import java.util.List;
import java.util.UUID;

import br.com.ufg.perguntinhas.records.PontuacaoRelativa;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ufg.perguntinhas.application.JogadorService;
import br.com.ufg.perguntinhas.records.JogadorRecord;
import br.com.ufg.perguntinhas.records.PontuacaoRecord;
import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@RequestMapping("/jogador")
public class JogadorController {

    private final JogadorService jogadorService;

    @PostMapping
    public ResponseEntity<JogadorRecord> criarJogador(@RequestBody JogadorRecord jogador) {
        var uuid = jogadorService.criarJogador(jogador);

        if (uuid != null) {
            return ResponseEntity.ok(new JogadorRecord(jogador.nome(), uuid.toString(),0.0, 0.0));
        }

        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/pontuacao")
    public ResponseEntity<List<PontuacaoRecord>> buscarRanking() {
        return ResponseEntity.ok(jogadorService.buscarRanking());
    }

    @GetMapping("/pontuacao/{uuid}")
    public ResponseEntity<PontuacaoRelativa> buscarPontuacaoJogador(@PathVariable String uuid) {

        var pontuacao = jogadorService.buscarPontuacaoJogador(uuid);

        if (pontuacao != null) {
            return ResponseEntity.ok(pontuacao);
        }

        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/ranking/{uuid}")
    public ResponseEntity<Long> findRankingByUuidJogador(@PathVariable String uuid) {
        return ResponseEntity.ok(jogadorService.findRankingByUuidJogador(UUID.fromString(uuid)));
    }
}
