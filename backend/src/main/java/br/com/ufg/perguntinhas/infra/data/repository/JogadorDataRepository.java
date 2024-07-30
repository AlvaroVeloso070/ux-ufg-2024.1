package br.com.ufg.perguntinhas.infra.data.repository;

import br.com.ufg.perguntinhas.infra.data.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface JogadorDataRepository extends JpaRepository<Jogador, UUID> {

    @Query(value = "SELECT j FROM Jogador j ORDER BY j.recordePontuacao DESC LIMIT 4")
    List<Jogador> buscarRanking();

    @Query(value = "WITH ranked_jogadores AS (" +
            "  SELECT uuid_jogador, recorde_pontuacao, " +
            "         ROW_NUMBER() OVER (ORDER BY recorde_pontuacao DESC) AS ranking " +
            "  FROM jogadores" +
            ") " +
            "SELECT ranking " +
            "FROM ranked_jogadores " +
            "WHERE uuid_jogador = :uuidJogador",
            nativeQuery = true)
    Long findRankingByUuidJogador(@Param("uuidJogador") UUID uuidJogador);
}
