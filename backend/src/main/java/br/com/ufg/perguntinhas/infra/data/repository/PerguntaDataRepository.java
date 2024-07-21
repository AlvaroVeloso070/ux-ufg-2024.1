package br.com.ufg.perguntinhas.infra.data.repository;

import br.com.ufg.perguntinhas.infra.data.Pergunta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PerguntaDataRepository extends CrudRepository<Pergunta, UUID>{
    @Query(value = "SELECT p FROM Pergunta p " +
                    "WHERE p.uuid NOT IN (SELECT r.pergunta.uuid FROM Resposta r WHERE r.jogador.uuid = :uuidJogador) " +
                    "ORDER BY FUNCTION('RANDOM')" +
                    "LIMIT 1")
    Optional<Pergunta> getPerguntaAleatoriaJogador(@Param("uuidJogador") UUID uuidJogador);

    @Query(value = "SELECT p FROM Pergunta p " +
                    "JOIN p.alternativas a " +
                    "WHERE a.uuid = :uuidAlternativa")
    Optional<Pergunta> getPerguntaPorAlternativa(@Param("uuidAlternativa") UUID uuid);
}
