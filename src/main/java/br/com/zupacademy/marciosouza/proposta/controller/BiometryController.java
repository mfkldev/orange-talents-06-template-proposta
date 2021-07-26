package br.com.zupacademy.marciosouza.proposta.controller;

import br.com.zupacademy.marciosouza.proposta.config.exception.ProposalNotFoundException;
import br.com.zupacademy.marciosouza.proposta.controller.dto.BiometryRequest;
import br.com.zupacademy.marciosouza.proposta.controller.dto.ProposalResponseWithBiometry;
import br.com.zupacademy.marciosouza.proposta.model.Biometry;
import br.com.zupacademy.marciosouza.proposta.model.Proposal;
import br.com.zupacademy.marciosouza.proposta.repository.ProposalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
public class BiometryController {

    @Autowired
    private ProposalRepository proposalRepository;

    @PostMapping("/biometria")
    @Transactional
    public ResponseEntity<?> save(@RequestParam String idCard, @RequestBody @Valid BiometryRequest biometryRequest, UriComponentsBuilder uriComponentsBuilder){

        Proposal proposal = proposalRepository.findByIdCard(idCard).orElseThrow(() -> new ProposalNotFoundException("Nenhuma proposta associada a esse cartão foi encontrada"));

        Biometry biometry = biometryRequest.toModel(proposal);

        proposal.setBiometry(biometry);
        proposalRepository.save(proposal);

        URI uri =  uriComponentsBuilder.path("/biometri/{id}").buildAndExpand(proposal.getId()).toUri();

        return ResponseEntity.created(uri).body(new ProposalResponseWithBiometry(proposal));
    }
}