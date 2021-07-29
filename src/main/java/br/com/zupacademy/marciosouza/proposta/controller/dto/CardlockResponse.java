package br.com.zupacademy.marciosouza.proposta.controller.dto;

import br.com.zupacademy.marciosouza.proposta.model.CardlockModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

public class CardlockResponse {

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalDateTime creationMoment;
    private String ipClient;
    private String userAgent;
    private String idCard;
    private String statusCardlock;

    public CardlockResponse(CardlockModel cardlockModel) {
        this.creationMoment = cardlockModel.getCreationMoment();
        this.ipClient = cardlockModel.getIpClient();
        this.userAgent = cardlockModel.getUserAgent();
        this.idCard = cardlockModel.getProposal().getIdCard();
        this.statusCardlock = cardlockModel.getStatusCardLock().toString();
    }

    public LocalDateTime getCreationMoment() {
        return creationMoment;
    }

    public String getIpClient() {
        return ipClient;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public String getIdCard() {
        return idCard;
    }

    public String getStatusCardlock() {
        return statusCardlock;
    }
}
