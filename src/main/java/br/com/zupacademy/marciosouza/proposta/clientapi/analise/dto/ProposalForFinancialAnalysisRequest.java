package br.com.zupacademy.marciosouza.proposta.clientapi.analise.dto;

import br.com.zupacademy.marciosouza.proposta.model.Proposal;

public class ProposalForFinancialAnalysisRequest {
    private String documento;
    private String nome;
    private Long idProposta;

    public ProposalForFinancialAnalysisRequest(Proposal proposal) {
        this.documento = proposal.getDocument();
        this.nome = proposal.getName();
        this.idProposta = proposal.getId();
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public Long getIdProposta() {
        return idProposta;
    }

    @Override
    public String toString() {
        return "ProposalForFinancialAnalysisRequest{" +
                "documento='" + documento + '\'' +
                ", nome='" + nome + '\'' +
                ", idProposta=" + idProposta +
                '}';
    }
}