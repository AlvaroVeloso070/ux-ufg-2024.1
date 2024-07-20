package br.com.ufg.perguntinhas.infra;

import br.com.ufg.perguntinhas.infra.data.Pergunta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PerguntaDataRepository extends CrudRepository<Pergunta, UUID>{
}
