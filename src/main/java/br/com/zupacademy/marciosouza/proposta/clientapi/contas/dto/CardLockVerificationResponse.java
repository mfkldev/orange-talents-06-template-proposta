package br.com.zupacademy.marciosouza.proposta.clientapi.contas.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.List;

public class CardLockVerificationResponse {

    private List<Bloqueios> bloqueios;

    public CardLockVerificationResponse(List<Bloqueios> bloqueios) {
        this.bloqueios = bloqueios;
    }

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public CardLockVerificationResponse() {
    }

    public List<Bloqueios> getBloqueios() {
        return bloqueios;
    }
}