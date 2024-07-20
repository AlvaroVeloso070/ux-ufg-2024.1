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
    @JoinColumn(name = "uuid_pergunta")
    private Pergunta pergunta;

    @ManyToOne
    @JoinColumn(name = "uuid_alternativa")
    private Alternativa alternativaRespondida;

    @ManyToOne
    @JoinColumn(name = "uuid_jogador")
    private Jogador jogador;

    @Column(name = "tempo_resposta")
    private Long tempoResposta;
}
