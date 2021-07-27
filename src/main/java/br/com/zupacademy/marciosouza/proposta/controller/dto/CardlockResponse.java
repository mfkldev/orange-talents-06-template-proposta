package br.com.zupacademy.marciosouza.proposta.controller.dto;

import br.com.zupacademy.marciosouza.proposta.model.Cardlock;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

public class CardlockResponse {

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalDateTime creationMoment;
    private String ipClient;
    private String userAgent;
    private String idCard;

    public CardlockResponse(Cardlock cardlock) {
        this.creationMoment = cardlock.getCreationMoment();
        this.ipClient = cardlock.getIpClient();
        this.userAgent = cardlock.getUserAgent();
        this.idCard = cardlock.getProposal().getIdCard();
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
}
