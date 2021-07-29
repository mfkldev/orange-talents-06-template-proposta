package br.com.zupacademy.marciosouza.proposta.clientapi.contas.dto;

import java.util.List;

public class AccountCardResponse {

    private List<Bloqueio> bloqueios;

    private List<Carteira> carteiras;

    public AccountCardResponse(List<Bloqueio> bloqueios, List<Carteira> carteiras) {
        this.bloqueios = bloqueios;
        this.carteiras = carteiras;
    }

    public AccountCardResponse() {
    }

    public List<Bloqueio> getBloqueios() {
        return bloqueios;
    }

    public List<Carteira> getCarteiras() {
        return carteiras;
    }
}