package br.com.zupacademy.marciosouza.proposta.clientapi.contas.feignclient;

import br.com.zupacademy.marciosouza.proposta.clientapi.contas.dto.CardCreationVerificationResponse;
import br.com.zupacademy.marciosouza.proposta.clientapi.contas.dto.CardLockVerificationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "AccountApi", url = "http://localhost:8888/api/cartoes")
public interface AccountApi {

    @GetMapping
    ResponseEntity<CardCreationVerificationResponse> cardCreationVerification(@RequestParam Long idProposta);

    @GetMapping("/{id}")
    ResponseEntity<CardLockVerificationResponse> cardLockVerification(@PathVariable String id);
}