package br.com.zupacademy.marciosouza.proposta.clientapi.contas.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

public class Bloqueio {

    private boolean ativo;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public Bloqueio(boolean ativo) {
        this.ativo = ativo;
    }

    public Bloqueio() {
    }

    public boolean isAtivo() { return ativo; }
}