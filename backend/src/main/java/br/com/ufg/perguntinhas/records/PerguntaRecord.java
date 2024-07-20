package br.com.ufg.perguntinhas.records;

import br.com.ufg.perguntinhas.infra.data.Pergunta;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record PerguntaRecord(
        UUID uuid,
        String enunciado,
        Set<AlternativaRecord> alternativas
)
{

    public Pergunta toEntity() {
        return Pergunta.builder()
                .uuid(uuid)
                .enunciado(enunciado)
                .alternativas(alternativas.stream().map(AlternativaRecord::toEntity).collect(Collectors.toSet()))
                .build();
    }
}
