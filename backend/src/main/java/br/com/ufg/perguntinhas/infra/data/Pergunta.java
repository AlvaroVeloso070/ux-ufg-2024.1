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
@Table(name = "perguntas")
public class Pergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "uuid_pergunta")
    private UUID uuid;

    @Column(name = "enunciado")
    private String enunciado;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "uuid_pergunta")
    private Set<Alternativa> alternativas;
}