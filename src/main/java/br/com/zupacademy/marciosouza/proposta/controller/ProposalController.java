package br.com.zupacademy.marciosouza.proposta.controller;

import br.com.zupacademy.marciosouza.proposta.controller.dto.ProposalRequest;
import br.com.zupacademy.marciosouza.proposta.controller.dto.ProposalResponse;
import br.com.zupacademy.marciosouza.proposta.model.Proposal;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
public class ProposalController {

    @PostMapping(value = "/proposta")
    @Transactional
    public ResponseEntity<?> registerProposal(@RequestBody @Valid ProposalRequest proposalRequest, UriComponentsBuilder uriComponentsBuilder){

        Proposal proposal = proposalRequest.toModel();

        URI uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(proposal.getId()).toUri();

        return ResponseEntity.created(uri).body(new ProposalResponse(proposal));
    }
}
