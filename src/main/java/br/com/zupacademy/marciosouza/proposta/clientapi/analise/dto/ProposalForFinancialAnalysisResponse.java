package br.com.zupacademy.marciosouza.proposta.clientapi.analise.dto;

public class ProposalForFinancialAnalysisResponse {

    private String resultadoSolicitacao;

    public ProposalForFinancialAnalysisResponse(String resultadoSolicitacao) {
        this.resultadoSolicitacao = resultadoSolicitacao;
    }

    @Deprecated
    public ProposalForFinancialAnalysisResponse() {
    }

    public String getResultadoSolicitacao() {
        return resultadoSolicitacao;
    }

    @Override
    public String toString() {
        return "ProposalForFinancialAnalysisResponse{" +
                "resultadoSolicitacao='" + resultadoSolicitacao + '\'' +
                '}';
    }
}
