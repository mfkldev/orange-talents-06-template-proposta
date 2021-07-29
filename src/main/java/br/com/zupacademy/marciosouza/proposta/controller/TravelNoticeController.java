package br.com.zupacademy.marciosouza.proposta.controller;

import br.com.zupacademy.marciosouza.proposta.clientapi.contas.feignclient.AccountApi;
import br.com.zupacademy.marciosouza.proposta.config.exception.ProposalNotFoundException;
import br.com.zupacademy.marciosouza.proposta.controller.dto.TravelNoticeRequest;
import br.com.zupacademy.marciosouza.proposta.controller.dto.TravelNoticeResponse;
import br.com.zupacademy.marciosouza.proposta.controller.service.IpRequest;
import br.com.zupacademy.marciosouza.proposta.model.ProposalModel;
import br.com.zupacademy.marciosouza.proposta.model.TravelNoticeModel;
import br.com.zupacademy.marciosouza.proposta.repository.ProposalRepository;
import br.com.zupacademy.marciosouza.proposta.repository.TravelNoticeRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class TravelNoticeController {

    @Autowired
    private ProposalRepository proposalRepository;

    @Autowired
    private TravelNoticeRepository travelNoticeRepository;

    @Autowired
    public AccountApi accountApi;

    @PostMapping("/aviso-viagem")
    @Transactional
    public ResponseEntity<?> save(@RequestParam String idcard, @RequestBody @Valid TravelNoticeRequest request, HttpServletRequest httpServletRequest){
        ProposalModel proposalModel = proposalRepository.findByIdCard(idcard).orElseThrow(() -> new ProposalNotFoundException("Nenhuma proposta associada a esse cartão foi encontrada"));

        String ipClient = IpRequest.getIpRequest(httpServletRequest);
        String userAgent = httpServletRequest.getHeader("User-Agent");

        TravelNoticeModel travelNoticeModel = new TravelNoticeModel(request, ipClient, userAgent, proposalModel);

        travelNoticeRequisition(idcard, request);

        travelNoticeRepository.save(travelNoticeModel);

        return ResponseEntity.ok(new TravelNoticeResponse(travelNoticeModel));
    }

    private void travelNoticeRequisition(String idcard, TravelNoticeRequest travelNoticeRequest) {
        try {
            accountApi.travelNoticeNotification(idcard, travelNoticeRequest);
        }catch (FeignException exception){
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Não foi possível notificar sobre a viagem.");
        }
    }
}
