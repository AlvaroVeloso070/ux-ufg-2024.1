package br.com.ufg.perguntinhas.infra.data.repository;

import br.com.ufg.perguntinhas.infra.data.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JogadorDataRepository extends JpaRepository<Jogador, UUID> {
}
