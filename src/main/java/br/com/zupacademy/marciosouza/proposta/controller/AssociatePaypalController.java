package br.com.zupacademy.marciosouza.proposta.controller;

import br.com.zupacademy.marciosouza.proposta.clientapi.contas.dto.AccountCardResponse;
import br.com.zupacademy.marciosouza.proposta.clientapi.contas.dto.AssociateWalletRequest;
import br.com.zupacademy.marciosouza.proposta.clientapi.contas.dto.WalletContaAPIReponse;
import br.com.zupacademy.marciosouza.proposta.clientapi.contas.feignclient.AccountApi;
import br.com.zupacademy.marciosouza.proposta.config.exception.ProposalNotFoundException;
import br.com.zupacademy.marciosouza.proposta.config.exception.UnprocessableEntityException;
import br.com.zupacademy.marciosouza.proposta.controller.dto.ProposalRequestForWalltet;
import br.com.zupacademy.marciosouza.proposta.controller.dto.WalletResponse;
import br.com.zupacademy.marciosouza.proposta.model.ProposalModel;
import br.com.zupacademy.marciosouza.proposta.model.Wallet;
import br.com.zupacademy.marciosouza.proposta.repository.ProposalRepository;
import br.com.zupacademy.marciosouza.proposta.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
public class AssociatePaypalController {

    @Autowired
    private ProposalRepository proposalRepository;

    @Autowired
    private AccountApi accountApi;

    @Autowired
    private WalletRepository walletRepository;

    @PostMapping("/carteira/paypal")
    @Transactional
    public ResponseEntity<?> associatePaypal(@RequestParam String idcard, @RequestBody @Valid ProposalRequestForWalltet proposalRequest, UriComponentsBuilder uriComponentsBuilder){

        String walletType = "PayPal";

        ProposalModel proposalModel = proposalRepository.findByIdCard(idcard).orElseThrow(() -> new ProposalNotFoundException("Nenhuma proposta associada a esse cartão foi encontrada"));
        if(!proposalModel.getEmail().equals(proposalRequest.getEmail())) throw new UnprocessableEntityException("Esse cartão não pertence ao email enviado");

        Wallet wallet = new Wallet(proposalModel, walletType);
        wallet.checkRecordWallet(idcard, accountApi, walletType);

        ResponseEntity<WalletContaAPIReponse> walletResponse = accountApi.associatePaypal(idcard, new AssociateWalletRequest(proposalRequest.getEmail(), walletType));

        if(walletResponse.getStatusCode() != HttpStatus.OK) throw new UnprocessableEntityException("Não foi possível associar a carteira");

        wallet.setIdWalletContasAPI(walletResponse.getBody().getId());
        walletRepository.save(wallet);

        URI uri = uriComponentsBuilder.path("/carteira/{id}").buildAndExpand(wallet.getId()).toUri();

        return ResponseEntity.created(uri).body(new WalletResponse(wallet));
    }
}