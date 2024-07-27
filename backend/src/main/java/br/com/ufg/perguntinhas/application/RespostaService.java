package br.com.ufg.perguntinhas.application;

import br.com.ufg.perguntinhas.enums.DificuldadeEnum;
import br.com.ufg.perguntinhas.infra.JogadorRepository;
import br.com.ufg.perguntinhas.infra.RespostaRepository;
import br.com.ufg.perguntinhas.records.RespostaRecord;
import br.com.ufg.perguntinhas.records.ResultadoRespostaRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static br.com.ufg.perguntinhas.enums.StatusRespostaEnum.CORRETA;
import static br.com.ufg.perguntinhas.enums.StatusRespostaEnum.ERRADA;

@Service
@RequiredArgsConstructor
public class RespostaService {

    private final AlternativaService alternativaService;
    private final RespostaRepository respostaRepository;
    private final JogadorRepository jogadorRepository;
    private final PerguntaService perguntaService;

    public ResultadoRespostaRecord responder(RespostaRecord resposta) {
        UUID uuidPergunta = perguntaService.getUUIDPerguntaPorAlternativa(resposta.uuidAlternativa());
        var pergunta = perguntaService.getPerguntaPorUuid(uuidPergunta);
        double tempo = Double.valueOf(resposta.tempoResposta());
        double dificuldade = (double) DificuldadeEnum.valueOf(pergunta.dificuldade()).getMultiplicador();
        Double pontuacao = (100 * (dificuldade / 4) * (1 - (tempo / 600000)));

        if (pergunta != null) {
            respostaRepository.incluirResposta(resposta, pergunta.uuid());

            if (alternativaService.isAlternativaCorreta(resposta.uuidAlternativa())) {
                jogadorRepository.updatePontuacaoJogador(resposta.uuidJogador(), pontuacao);
                return new ResultadoRespostaRecord(CORRETA.name(), pontuacao);
            } else {
                return new ResultadoRespostaRecord(ERRADA.name(), 0.0);
            }
        }

        return null;
    }
}
