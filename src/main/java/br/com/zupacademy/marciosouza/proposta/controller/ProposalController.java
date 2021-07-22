package br.com.zupacademy.marciosouza.proposta.controller;

import br.com.zupacademy.marciosouza.proposta.clientapi.analise.dto.ProposalForFinancialAnalysisRequest;
import br.com.zupacademy.marciosouza.proposta.clientapi.analise.dto.ProposalForFinancialAnalysisResponse;
import br.com.zupacademy.marciosouza.proposta.clientapi.analise.feignclient.FinancialAnalysisApi;
import br.com.zupacademy.marciosouza.proposta.controller.dto.ProposalRequest;
import br.com.zupacademy.marciosouza.proposta.controller.dto.ProposalResponse;
import br.com.zupacademy.marciosouza.proposta.model.Proposal;
import br.com.zupacademy.marciosouza.proposta.repository.ProposalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/proposta")
public class ProposalController {

    @Autowired
    private ProposalRepository proposalRepository;

    @Autowired
    private FinancialAnalysisApi financialAnalysisApi;

    @PostMapping
    @Transactional
    public ResponseEntity<?> registerProposal(@RequestBody @Valid ProposalRequest proposalRequest, UriComponentsBuilder uriComponentsBuilder){

        Proposal proposal = proposalRequest.toModel();

        URI uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(proposal.getId()).toUri();

        proposalRepository.save(proposal);

        ProposalForFinancialAnalysisResponse response = financialAnalysisApi.verificationFinancialAnalysis(new ProposalForFinancialAnalysisRequest(proposal));

        proposal.setStatus(response.getResultadoSolicitacao());

        return ResponseEntity.created(uri).body(new ProposalResponse(proposal));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> proposedConsultation(@PathVariable Long id){

        Optional<Proposal> proposal = proposalRepository.findById(id);

        if (proposal.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Proposta não encontrada");
        }

        return ResponseEntity.ok(new ProposalResponse(proposal.get()));
    }
}