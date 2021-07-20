package br.com.zupacademy.marciosouza.proposta.clientapi.analise.request;

import br.com.zupacademy.marciosouza.proposta.clientapi.analise.dto.ProposalForFinancialAnalysisRequest;
import br.com.zupacademy.marciosouza.proposta.clientapi.analise.dto.ProposalForFinancialAnalysisResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "solicitacao", url = "http://localhost:9999/api")
public interface FinancialAnalysis {

    @RequestMapping(method = RequestMethod.POST, value = "/solicitacao")
    ProposalForFinancialAnalysisResponse verificationFinancialAnalysis(@RequestBody ProposalForFinancialAnalysisRequest proposalForFinancialAnalysisRequest);
}

