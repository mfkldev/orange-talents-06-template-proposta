package br.com.zupacademy.marciosouza.proposta.clientapi.contas.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

public class CardCreationVerificationResponse {

    private String id;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public CardCreationVerificationResponse(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
