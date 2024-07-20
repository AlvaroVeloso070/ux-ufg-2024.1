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

    public static PerguntaRecord toRecord(Pergunta pergunta) {
        return new PerguntaRecord(
                pergunta.getUuid(),
                pergunta.getEnunciado(),
                pergunta.getAlternativas().stream().map(AlternativaRecord::toRecord).collect(Collectors.toSet()),
                pergunta.getDificuldadeEnum().name(),
                pergunta.getCategoriaEnum().name()
        );
    }

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
        try{
            return DificuldadeEnum.valueOf(dificuldade);
        } catch (Exception e) {
            return null;
        }
    }

    private CategoriaEnum getEnumCategoria(String categoria) {
        try{
            return CategoriaEnum.valueOf(categoria);
        }catch (Exception e){
            return null;
        }
    }
}
