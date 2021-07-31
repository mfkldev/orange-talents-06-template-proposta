package br.com.zupacademy.marciosouza.proposta.controller;

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
import feign.FeignException;
import io.opentracing.Span;
import io.opentracing.Tracer;
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
public class AssociateWalletlController {

    @Autowired
    private ProposalRepository proposalRepository;

    @Autowired
    private AccountApi accountApi;

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private Tracer tracer;


    @PostMapping("/carteira/paypal")
    @Transactional
    public ResponseEntity<?> associatePaypal(@RequestParam String idcard, @RequestBody @Valid ProposalRequestForWalltet proposalRequest, UriComponentsBuilder uriComponentsBuilder){

        return performWalletAssociation(idcard, proposalRequest, uriComponentsBuilder, "PayPal");
    }

    @PostMapping("/carteira/samsung-pay")
    @Transactional
    public ResponseEntity<?> associateSamsungPay(@RequestParam String idcard, @RequestBody @Valid ProposalRequestForWalltet proposalRequest, UriComponentsBuilder uriComponentsBuilder){

        return performWalletAssociation(idcard, proposalRequest, uriComponentsBuilder, "SamsungPay");
    }

    private ResponseEntity<?> performWalletAssociation(@RequestParam String idcard, @RequestBody @Valid ProposalRequestForWalltet proposalRequest, UriComponentsBuilder uriComponentsBuilder, String walletType) {
        Span activeSpan = tracer.activeSpan();
        activeSpan.setTag("user.email", proposalRequest.getEmail());
        activeSpan.setBaggageItem("user.email", proposalRequest.getEmail());
        activeSpan.log("Log de Marcio Franklin");

        Wallet wallet = createWallet(idcard, proposalRequest, walletType);

        walletRepository.save(wallet);

        URI uri = uriComponentsBuilder.path("/carteira/{id}").buildAndExpand(wallet.getId()).toUri();

        return ResponseEntity.created(uri).body(new WalletResponse(wallet));
    }

    private Wallet createWallet(String idcard, ProposalRequestForWalltet proposalRequest, String walletType) {
        ProposalModel proposalModel = checkInconsistency(idcard, proposalRequest);

        String idWalletAPI = associateWallet(idcard, proposalRequest, walletType).getBody().getId();

        return new Wallet(proposalModel, walletType, idWalletAPI);
    }

    private ProposalModel checkInconsistency(String idcard, ProposalRequestForWalltet proposalRequest) {
        ProposalModel proposalModel = proposalRepository.findByIdCard(idcard).orElseThrow(() -> new ProposalNotFoundException("Nenhuma proposta associada a esse cartão foi encontrada"));
        if(!proposalModel.getEmail().equals(proposalRequest.getEmail())) throw new UnprocessableEntityException("Esse cartão não pertence ao email enviado");
        return proposalModel;
    }

    private ResponseEntity<WalletContaAPIReponse> associateWallet(String idcard, ProposalRequestForWalltet proposalRequest, String walletType) {
        ResponseEntity<WalletContaAPIReponse> walletResponse;
        
        try{
             walletResponse = accountApi.associatePaypal(idcard, new AssociateWalletRequest(proposalRequest.getEmail(), walletType));
        }catch (FeignException exception){
            if(exception.status() == 422) throw new UnprocessableEntityException("A carteira já se encontra associada a esse cartão");
            throw new UnprocessableEntityException("Não foi possível associar a carteira");
        }

        return walletResponse;
    }
}