package br.com.zupacademy.marciosouza.proposta.controller;

import br.com.zupacademy.marciosouza.proposta.clientapi.contas.feignclient.AccountApi;
import br.com.zupacademy.marciosouza.proposta.config.exception.ProposalNotFoundException;
import br.com.zupacademy.marciosouza.proposta.controller.dto.CardlockResponse;
import br.com.zupacademy.marciosouza.proposta.model.Cardlock;
import br.com.zupacademy.marciosouza.proposta.model.Proposal;
import br.com.zupacademy.marciosouza.proposta.repository.CardlockRepository;
import br.com.zupacademy.marciosouza.proposta.repository.ProposalRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

@RestController
public class CardlockVerificationController {

    @Autowired
    public ProposalRepository proposalRepository;

    @Autowired
    public CardlockRepository cardlockRepository;

    @Autowired
    public AccountApi accountApi;

    @PostMapping("/bloqueio")
    @Transactional
    public ResponseEntity<?> save(@RequestParam String idcard, HttpServletRequest httpServletRequest) {

        Proposal proposal = proposalRepository.findByIdCard(idcard).orElseThrow(() -> new ProposalNotFoundException("Nenhuma proposta associada a esse cartão foi encontrada"));

        String ipClient = getIpRequest(httpServletRequest);
        String userAgent = httpServletRequest.getHeader("User-Agent");

        Cardlock cardlock = new Cardlock(ipClient, userAgent, proposal);
        cardlock.cardLockedVerication(idcard, accountApi);

        cardLockRequisition(idcard);

        cardlockRepository.save(cardlock);

        return ResponseEntity.ok(new CardlockResponse(cardlock));
    }

    private void cardLockRequisition(String idcard) {
        try {
            accountApi.requestCardLock(idcard, Map.of("sistemaResponsavel", "Api-proposta"));
        }catch (FeignException exception){
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Não foi possível notificar o bloqueio");
        }
    }

    private String getIpRequest(HttpServletRequest request) {

        return Stream.of(
                request.getHeader("x-forwarded-for"),
                request.getHeader("X_FORWARDED_FOR"),
                request.getRemoteAddr(),
                "Infelizmente não foi possível capturar o IP"
        ).filter(Objects::nonNull).findFirst().get();
    }
}