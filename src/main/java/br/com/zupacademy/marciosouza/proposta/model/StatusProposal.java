package br.com.zupacademy.marciosouza.proposta.model;

public enum StatusProposal {
    NAO_ELEGIVEL,
    ELEGIVEL;

    public static StatusProposal getStatus(String statusProposalApiFinancialAnalysis){
        return statusProposalApiFinancialAnalysis.equals("SEM_RESTRICAO") ? ELEGIVEL : NAO_ELEGIVEL;
    }
}
