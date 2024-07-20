package br.com.ufg.perguntinhas.infra.data.repository;

import br.com.ufg.perguntinhas.infra.data.Jogador;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface JogadorDataRepository extends CrudRepository<Jogador, UUID>{
}
