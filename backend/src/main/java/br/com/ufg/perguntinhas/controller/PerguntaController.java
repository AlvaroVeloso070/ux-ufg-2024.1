package br.com.ufg.perguntinhas.controller;

import br.com.ufg.perguntinhas.application.PerguntaService;
import br.com.ufg.perguntinhas.records.PerguntaRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/pergunta")
@RequiredArgsConstructor
public class PerguntaController {

    private final PerguntaService perguntaService;

    @PostMapping
    public ResponseEntity<?> createPergunta(@RequestBody PerguntaRecord pergunta) {
        Set<String> erros =  perguntaService.save(pergunta);
        if (erros.isEmpty()) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().body(erros);
    }

    @PostMapping("/batch")
    public ResponseEntity<?> createPerguntas(@RequestBody List<PerguntaRecord> perguntas) {
        Set<String> erros =  perguntaService.saveAll(perguntas);
        if (erros.isEmpty()) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().body(erros);
    }

    @GetMapping("/random/{uuidJogador}")
    public ResponseEntity<PerguntaRecord> getPerguntaAleatoriaJogador(@PathVariable String uuidJogador) {
        return ResponseEntity.ok(perguntaService.getPerguntaAleatoriaJogador(uuidJogador));
    }
}
