package br.com.zupacademy.marciosouza.proposta.clientapi.contas.dto;

import br.com.zupacademy.marciosouza.proposta.model.ProposalModel;
import com.fasterxml.jackson.annotation.JsonCreator;

public class ElegibleProposalRequest {

    private Long id;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public ElegibleProposalRequest(ProposalModel oneEligibleProposalModel) {
        this.id = oneEligibleProposalModel.getId();
    }

    public Long getId() {
        return id;
    }
}
