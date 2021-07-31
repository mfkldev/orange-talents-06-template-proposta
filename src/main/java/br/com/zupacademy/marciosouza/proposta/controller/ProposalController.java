package br.com.zupacademy.marciosouza.proposta.controller;

import br.com.zupacademy.marciosouza.proposta.clientapi.analise.dto.ProposalForFinancialAnalysisRequest;
import br.com.zupacademy.marciosouza.proposta.clientapi.analise.dto.ProposalForFinancialAnalysisResponse;
import br.com.zupacademy.marciosouza.proposta.clientapi.analise.feignclient.FinancialAnalysisApi;
import br.com.zupacademy.marciosouza.proposta.config.exception.UnprocessableEntityException;
import br.com.zupacademy.marciosouza.proposta.controller.dto.ProposalRequest;
import br.com.zupacademy.marciosouza.proposta.controller.dto.ProposalResponse;
import br.com.zupacademy.marciosouza.proposta.controller.usecase.CryptDocumentUsecase;
import br.com.zupacademy.marciosouza.proposta.model.ProposalModel;
import br.com.zupacademy.marciosouza.proposta.repository.ProposalRepository;
import io.opentracing.Span;
import io.opentracing.Tracer;
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

    @Autowired
    private Tracer tracer;

    @PostMapping
    @Transactional
    public ResponseEntity<?> registerProposal(@RequestBody @Valid ProposalRequest proposalRequest, UriComponentsBuilder uriComponentsBuilder){
        Span activeSpan = tracer.activeSpan();
        activeSpan.setTag("user.email", proposalRequest.getEmail());
        activeSpan.setBaggageItem("user.email", proposalRequest.getEmail());
        activeSpan.log("Log de Marcio Franklin");

        String documentEncrypted = CryptDocumentUsecase.encryptDocument(proposalRequest.getDocument());

        if(proposalRepository.findByDocument(documentEncrypted).isPresent()) throw new UnprocessableEntityException("Já existe uma proposta associada a esse documetno");

        ProposalModel proposalModel = proposalRequest.toModel();

        URI uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(proposalModel.getId()).toUri();

        proposalRepository.save(proposalModel);

        ProposalForFinancialAnalysisResponse response = financialAnalysisApi.verificationFinancialAnalysis(new ProposalForFinancialAnalysisRequest(proposalModel));

        proposalModel.setStatus(response.getResultadoSolicitacao());

        return ResponseEntity.created(uri).body(new ProposalResponse(proposalModel));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> proposedConsultation(@PathVariable Long id){
        Span activeSpan = tracer.activeSpan();

        Optional<ProposalModel> proposal = proposalRepository.findById(id);

        if (proposal.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Proposta não encontrada");
        }

        activeSpan.setTag("user.email", proposal.get().getEmail());

        return ResponseEntity.ok(new ProposalResponse(proposal.get()));
    }
}