package br.com.ufg.perguntinhas.application;

import br.com.ufg.perguntinhas.enums.CategoriaEnum;
import br.com.ufg.perguntinhas.enums.DificuldadeEnum;
import br.com.ufg.perguntinhas.infra.PerguntaRepository;
import br.com.ufg.perguntinhas.records.AlternativaRecord;
import br.com.ufg.perguntinhas.records.PerguntaRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PerguntaService {

    private final PerguntaRepository perguntaRepository;

    public Set<String> saveAll(List<PerguntaRecord> perguntas) {
        Set<String> erros =new HashSet<>();
        perguntas.forEach(pergunta -> {
            if (perguntaValida(pergunta, erros)) {
                perguntaRepository.save(pergunta);
            }
        });
        return erros;
    }

    private boolean perguntaValida(PerguntaRecord pergunta, Set<String> erros) {
        if (pergunta.enunciado().isBlank()) {
            erros.add("A pergunta deve ter um enunciado");
            return false;
        }

        if (pergunta.alternativas().size() != 4){
            erros.add("A pergunta \"" + pergunta.enunciado() + "\" deve ter 4 alternativas distintas.");
            return false;
        }
        if (!somenteUmaAlternativaCorreta(pergunta)) {
            erros.add("A pergunta \"" + pergunta.enunciado() + "\" deve ter exatamente uma alternativa correta");
            return false;
        }

        if (pergunta.alternativas().stream().anyMatch(alt -> alt.descricao().isBlank())) {
            erros.add("A pergunta \"" + pergunta.enunciado() + "\" deve ter todas as alternativas preenchidas");
            return false;
        }

        if (pergunta.dificuldade().isBlank()){
            erros.add("A pergunta \"" + pergunta.enunciado() + "\" deve ter uma dificuldade");
            return false;
        }

        try {
            DificuldadeEnum.valueOf(pergunta.dificuldade());
        } catch (IllegalArgumentException e) {
            erros.add("A dificuldade \"" + pergunta.dificuldade() + "\" não é válida");
            return false;
        }

        if (pergunta.categoria().isBlank()){
            erros.add("A pergunta \"" + pergunta.enunciado() + "\" deve ter uma categoria");
            return false;
        }

        try {
            CategoriaEnum.valueOf(pergunta.categoria());
        } catch (IllegalArgumentException e) {
            erros.add("A categoria \"" + pergunta.categoria() + "\" não é válida");
            return false;
        }

        return true;
    }

    private boolean somenteUmaAlternativaCorreta(PerguntaRecord pergunta) {
        return pergunta.alternativas().stream().filter(AlternativaRecord::correta).count() == 1L;
    }

    public Set<String> save(PerguntaRecord pergunta) {
        Set<String> erros = new HashSet<>();
        if (perguntaValida(pergunta, erros)) {
            perguntaRepository.save(pergunta);
        }
        return erros;
    }

    public PerguntaRecord getPerguntaAleatoriaJogador(String uuidJogador) {
        return perguntaRepository.getPerguntaAleatoriaJogador(uuidJogador);
    }

    public PerguntaRecord getPerguntaPorUuid(UUID uuidPergunta) {
        return perguntaRepository.getPerguntaPorUuid(uuidPergunta);
    }

    public UUID getUUIDPerguntaPorAlternativa(String uuidAlternativa) {
        return perguntaRepository.getPerguntaPorAlternativa(uuidAlternativa);
    }
}
