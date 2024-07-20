package br.com.ufg.perguntinhas.infra.data;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "respostas")
public class Resposta {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "uuid_resposta")
    private UUID uuid;

    @ManyToOne
    @JoinColumn(name = "uuid_pergunta", nullable = false)
    private Pergunta pergunta;

    @ManyToOne
    @JoinColumn(name = "uuid_alternativa", nullable = false)
    private Alternativa alternativaRespondida;

    @ManyToOne
    @JoinColumn(name = "uuid_jogador", nullable = false)
    private Jogador jogador;

    @Column(name = "tempo_resposta", nullable = false)
    private Long tempoResposta;
}
