package br.com.ufg.perguntinhas.application;

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
    private final PerguntaService perguntaService;

    public ResultadoRespostaRecord responder(RespostaRecord resposta) {
        UUID uuidPergunta = perguntaService.getUUIDPerguntaPorAlternativa(resposta.uuidAlternativa());
        if (uuidPergunta != null) {
            respostaRepository.incluirResposta(resposta, uuidPergunta);

            if (alternativaService.isAlternativaCorreta(resposta.uuidAlternativa())){
                return new ResultadoRespostaRecord(CORRETA.name(), 0.0);
            }else {
                return new ResultadoRespostaRecord(ERRADA.name(), 0.0);
            }
        }

        return null;
    }
}
