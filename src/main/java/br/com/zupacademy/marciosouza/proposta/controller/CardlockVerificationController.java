package br.com.zupacademy.marciosouza.proposta.controller;

import br.com.zupacademy.marciosouza.proposta.clientapi.contas.feignclient.AccountApi;
import br.com.zupacademy.marciosouza.proposta.config.exception.ProposalNotFoundException;
import br.com.zupacademy.marciosouza.proposta.controller.dto.CardlockResponse;
import br.com.zupacademy.marciosouza.proposta.model.Cardlock;
import br.com.zupacademy.marciosouza.proposta.model.Proposal;
import br.com.zupacademy.marciosouza.proposta.repository.CardlockRepository;
import br.com.zupacademy.marciosouza.proposta.repository.ProposalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
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
    public ResponseEntity<?> save(@RequestParam String idcard, HttpServletRequest httpServletRequest) {

        Proposal proposal = proposalRepository.findByIdCard(idcard).orElseThrow(() -> new ProposalNotFoundException("Nenhuma proposta associada a esse cartão foi encontrada"));

        String ipClient = getIpRequest(httpServletRequest);
        String userAgent = httpServletRequest.getHeader("User-Agent");

        Cardlock cardlock = new Cardlock(ipClient, userAgent, proposal);
        cardlock.cardLockedVerication(idcard, accountApi);
        cardlockRepository.save(cardlock);

        return ResponseEntity.ok(new CardlockResponse(cardlock));
    }

    private String getIpRequest(HttpServletRequest request) {

        return Stream.of(
                request.getHeader("x-forwarded-for"),
                request.getHeader("X_FORWARDED_FOR"),
                request.getRemoteAddr()
        ).filter(Objects::nonNull).findFirst().get();
    }
}