package br.com.zupacademy.marciosouza.proposta.clientapi.contas.dto;

import br.com.zupacademy.marciosouza.proposta.model.Proposal;
import com.fasterxml.jackson.annotation.JsonCreator;

public class ElegibleProposalRequest {

    private Long id;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public ElegibleProposalRequest(Proposal oneEligibleProposal) {
        this.id = oneEligibleProposal.getId();
    }

    public Long getId() {
        return id;
    }
}
