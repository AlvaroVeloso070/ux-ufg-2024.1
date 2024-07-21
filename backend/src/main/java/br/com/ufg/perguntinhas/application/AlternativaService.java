package br.com.ufg.perguntinhas.application;

import br.com.ufg.perguntinhas.infra.AlternativaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlternativaService {

    private final AlternativaRepository alternativaRepository;

    public boolean isAlternativaCorreta(String uuidAlternativa) {
        return alternativaRepository.isAlternativaCorreta(uuidAlternativa);
    }
}
