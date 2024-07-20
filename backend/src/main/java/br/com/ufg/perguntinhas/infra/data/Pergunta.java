package br.com.ufg.perguntinhas.infra.data;

import br.com.ufg.perguntinhas.enums.CategoriaEnum;
import br.com.ufg.perguntinhas.enums.DificuldadeEnum;
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

    @Column(name = "enunciado", nullable = false, columnDefinition = "TEXT")
    private String enunciado;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "uuid_pergunta", nullable = false)
    private Set<Alternativa> alternativas;

    @Enumerated(EnumType.STRING)
    @Column(name = "dificuldade", nullable = false)
    private DificuldadeEnum dificuldadeEnum;

    @Enumerated(EnumType.STRING)
    @Column(name = "categoria", nullable = false)
    private CategoriaEnum categoriaEnum;
}
