package br.com.ufg.perguntinhas.infra.data.repository;

import br.com.ufg.perguntinhas.infra.data.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface JogadorDataRepository extends JpaRepository<Jogador, UUID> {

    @Query(value = "SELECT j FROM Jogador j ORDER BY j.recordePontuacao DESC LIMIT 4")
    List<Jogador> buscarRanking();
}
