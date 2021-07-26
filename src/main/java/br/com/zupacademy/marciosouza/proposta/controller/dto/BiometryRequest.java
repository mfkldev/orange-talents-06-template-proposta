package br.com.zupacademy.marciosouza.proposta.controller.dto;

import br.com.zupacademy.marciosouza.proposta.config.validation.isBase64;
import br.com.zupacademy.marciosouza.proposta.model.Biometry;
import br.com.zupacademy.marciosouza.proposta.model.Proposal;
import com.fasterxml.jackson.annotation.JsonCreator;
import javax.validation.constraints.NotBlank;

public class BiometryRequest{

    @NotBlank @isBase64
    private String biometry;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public BiometryRequest(@NotBlank String biometry) {
        this.biometry = biometry;
    }

    public Biometry toModel(Proposal proposal){
        return new Biometry(this.biometry, proposal);
    }
}