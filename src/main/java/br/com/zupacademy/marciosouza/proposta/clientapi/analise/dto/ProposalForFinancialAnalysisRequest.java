package br.com.zupacademy.marciosouza.proposta.clientapi.analise.dto;

import br.com.zupacademy.marciosouza.proposta.model.ProposalModel;

public class ProposalForFinancialAnalysisRequest {
    private String documento;
    private String nome;
    private Long idProposta;

    public ProposalForFinancialAnalysisRequest(ProposalModel proposalModel) {
        this.documento = proposalModel.getDocument();
        this.nome = proposalModel.getName();
        this.idProposta = proposalModel.getId();
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