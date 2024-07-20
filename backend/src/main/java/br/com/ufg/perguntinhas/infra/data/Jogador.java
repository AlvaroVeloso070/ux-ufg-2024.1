package br.com.ufg.perguntinhas.infra.data;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "jogadores")
public class Jogador {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "uuid_jogador")
    private UUID uuid;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "pontuacao")
    public Double pontuacao;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "uuid_jogador")
    private Set<Resposta> respostas;
}
