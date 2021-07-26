package br.com.zupacademy.marciosouza.proposta.clientapi.analise.feignclient;

import br.com.zupacademy.marciosouza.proposta.clientapi.analise.dto.ProposalForFinancialAnalysisRequest;
import br.com.zupacademy.marciosouza.proposta.clientapi.analise.dto.ProposalForFinancialAnalysisResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "requestFinancialAnalysis", url = "http://localhost:9999/api/")
public interface FinancialAnalysisApi {

    @PostMapping(value = "solicitacao")
    ProposalForFinancialAnalysisResponse verificationFinancialAnalysis(@RequestBody ProposalForFinancialAnalysisRequest proposalForFinancialAnalysisRequest);
}

