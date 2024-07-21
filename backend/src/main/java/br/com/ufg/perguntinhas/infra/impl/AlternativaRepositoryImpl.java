package br.com.ufg.perguntinhas.infra.impl;

import br.com.ufg.perguntinhas.infra.AlternativaRepository;
import br.com.ufg.perguntinhas.infra.data.Alternativa;
import br.com.ufg.perguntinhas.infra.data.repository.AlternativaDataReporitory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class AlternativaRepositoryImpl implements AlternativaRepository {

    private final AlternativaDataReporitory alternativaDataReporitory;

    @Override
    public boolean isAlternativaCorreta(String uuidAlternativa) {
        Optional<Alternativa> alternativa = alternativaDataReporitory.findById(UUID.fromString(uuidAlternativa));
        return alternativa.map(Alternativa::isCorreta).orElse(false);
    }
}
