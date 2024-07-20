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
@Table(name = "alternativas")
public class Alternativa {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "uuid_alternativa")
    private UUID uuid;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "correta", nullable = false)
    private boolean correta;

}
