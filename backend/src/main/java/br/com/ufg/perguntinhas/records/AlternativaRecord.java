package br.com.ufg.perguntinhas.records;

import br.com.ufg.perguntinhas.infra.data.Alternativa;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record AlternativaRecord(
        UUID uuid,
        String descricao,
        Boolean correta
)
{
    public Alternativa toEntity() {
        return Alternativa.builder()
                .uuid(uuid)
                .descricao(descricao)
                .correta(correta)
                .build();
    }

    public static AlternativaRecord toRecord(Alternativa alternativa) {
        return new AlternativaRecord(
                alternativa.getUuid(),
                alternativa.getDescricao(),
                null
        );
    }
}
