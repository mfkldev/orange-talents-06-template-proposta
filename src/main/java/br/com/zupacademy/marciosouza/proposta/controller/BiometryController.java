package br.com.zupacademy.marciosouza.proposta.controller;

import br.com.zupacademy.marciosouza.proposta.config.exception.ProposalNotFoundException;
import br.com.zupacademy.marciosouza.proposta.controller.dto.BiometryRequest;
import br.com.zupacademy.marciosouza.proposta.controller.dto.ProposalResponseWithBiometry;
import br.com.zupacademy.marciosouza.proposta.model.BiometryModel;
import br.com.zupacademy.marciosouza.proposta.model.ProposalModel;
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
    public ResponseEntity<?> save(@RequestParam String idcard, @RequestBody @Valid BiometryRequest biometryRequest, UriComponentsBuilder uriComponentsBuilder){

        ProposalModel proposalModel = proposalRepository.findByIdCard(idcard).orElseThrow(() -> new ProposalNotFoundException("Nenhuma proposta associada a esse cartão foi encontrada"));

        BiometryModel biometryModel = biometryRequest.toModel(proposalModel);

        proposalModel.setBiometry(biometryModel);
        proposalRepository.save(proposalModel);

        URI uri =  uriComponentsBuilder.path("/biometri/{id}").buildAndExpand(proposalModel.getId()).toUri();

        return ResponseEntity.created(uri).body(new ProposalResponseWithBiometry(proposalModel));
    }
}