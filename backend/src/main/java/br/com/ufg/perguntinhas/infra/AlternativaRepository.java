package br.com.ufg.perguntinhas.infra;

public interface AlternativaRepository {
    boolean isAlternativaCorreta(String uuidAlternativa);
}
