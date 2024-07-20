package br.com.ufg.perguntinhas.records;

import br.com.ufg.perguntinhas.enums.CategoriaEnum;
import br.com.ufg.perguntinhas.enums.DificuldadeEnum;
import br.com.ufg.perguntinhas.infra.data.Pergunta;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record PerguntaRecord(
        UUID uuid,
        String enunciado,
        Set<AlternativaRecord> alternativas,
        String dificuldade,
        String categoria
)
{

    public Pergunta toEntity() {
        return Pergunta.builder()
                .uuid(uuid)
                .enunciado(enunciado)
                .alternativas(alternativas.stream().map(AlternativaRecord::toEntity).collect(Collectors.toSet()))
                .dificuldadeEnum(getEnumDificuldade(dificuldade))
                .categoriaEnum(getEnumCategoria(categoria))
                .build();
    }

    private DificuldadeEnum getEnumDificuldade(String dificuldade) {
        return DificuldadeEnum.valueOf(dificuldade);
    }

    private CategoriaEnum getEnumCategoria(String categoria) {
        return CategoriaEnum.valueOf(categoria);
    }
}
