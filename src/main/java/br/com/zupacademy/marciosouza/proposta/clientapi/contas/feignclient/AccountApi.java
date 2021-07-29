package br.com.zupacademy.marciosouza.proposta.clientapi.contas.feignclient;

import br.com.zupacademy.marciosouza.proposta.clientapi.contas.dto.CardCreationVerificationResponse;
import br.com.zupacademy.marciosouza.proposta.clientapi.contas.dto.CardLockVerificationResponse;
import br.com.zupacademy.marciosouza.proposta.clientapi.contas.dto.CardlockRequest;
import br.com.zupacademy.marciosouza.proposta.controller.dto.TravelNoticeRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "AccountApi", url = "http://localhost:8888/api/cartoes")
public interface AccountApi {

    @GetMapping
    ResponseEntity<CardCreationVerificationResponse> cardCreationVerification(@RequestParam Long idProposta);

    @GetMapping("/{id}")
    ResponseEntity<CardLockVerificationResponse> cardLockVerification(@PathVariable String id);

    @PostMapping("/{id}/bloqueios")
    void requestCardLock(@PathVariable String id, @RequestBody CardlockRequest request);

    @PostMapping("/{id}/avisos")
    void travelNoticeNotification(@PathVariable String id, @RequestBody TravelNoticeRequest request);
}