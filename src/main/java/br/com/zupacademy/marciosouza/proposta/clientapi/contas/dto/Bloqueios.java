package br.com.zupacademy.marciosouza.proposta.clientapi.contas.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

public class Bloqueios {

    private boolean ativo;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public Bloqueios(boolean ativo) {
        this.ativo = ativo;
    }

    public Bloqueios() {
    }

    public boolean isAtivo() { return ativo; }
}