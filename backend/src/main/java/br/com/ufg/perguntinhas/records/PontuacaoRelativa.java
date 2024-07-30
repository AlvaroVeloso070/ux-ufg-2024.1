package br.com.ufg.perguntinhas.records;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record PontuacaoRelativa (String uuid, String nomeJogador, Double pontuacao,int posicao){

}
