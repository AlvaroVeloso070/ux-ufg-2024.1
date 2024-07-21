package br.com.ufg.perguntinhas.controller;

import br.com.ufg.perguntinhas.application.RespostaService;
import br.com.ufg.perguntinhas.records.RespostaRecord;
import br.com.ufg.perguntinhas.records.ResultadoRespostaRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resposta")
@RequiredArgsConstructor
public class RespostaController {

    private final RespostaService respostaService;

    @PostMapping
    public ResponseEntity<ResultadoRespostaRecord> createResposta(@RequestBody RespostaRecord resposta) {
        return ResponseEntity.ok(respostaService.responder(resposta));
    }
}
