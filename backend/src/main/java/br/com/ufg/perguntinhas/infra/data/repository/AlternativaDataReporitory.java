package br.com.ufg.perguntinhas.infra.data.repository;

import br.com.ufg.perguntinhas.infra.data.Alternativa;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AlternativaDataReporitory extends CrudRepository<Alternativa, UUID> {
}
