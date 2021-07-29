package br.com.zupacademy.marciosouza.proposta.controller.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class ProposalRequestForWalltet {

    @Email @NotBlank
    private String email;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public ProposalRequestForWalltet(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
