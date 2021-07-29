package br.com.zupacademy.marciosouza.proposta.controller.dto;

import br.com.zupacademy.marciosouza.proposta.config.validation.IsBase64;
import br.com.zupacademy.marciosouza.proposta.model.BiometryModel;
import br.com.zupacademy.marciosouza.proposta.model.ProposalModel;
import com.fasterxml.jackson.annotation.JsonCreator;
import javax.validation.constraints.NotBlank;

public class BiometryRequest{

    @NotBlank @IsBase64
    private String biometry;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public BiometryRequest(@NotBlank String biometry) {
        this.biometry = biometry;
    }

    public BiometryModel toModel(ProposalModel proposalModel){
        return new BiometryModel(this.biometry, proposalModel);
    }
}