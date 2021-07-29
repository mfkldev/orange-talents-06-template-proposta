package br.com.zupacademy.marciosouza.proposta.controller.dto;

import br.com.zupacademy.marciosouza.proposta.model.TravelNoticeModel;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TravelNoticeResponse {

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalDateTime creationMomen;
    private String destiny;

    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate tripEndDate;
    private String userAgent;
    private String ipClient;
    private String emailProposal;

    public TravelNoticeResponse(TravelNoticeModel travelNoticeModel) {
        this.creationMomen = travelNoticeModel.getCreationMoment();
        this.destiny = travelNoticeModel.getDestiny();
        this.tripEndDate = travelNoticeModel.getTripEndDate();
        this.userAgent = travelNoticeModel.getUserAgent();
        this.ipClient = travelNoticeModel.getIpClient();
        this.emailProposal = travelNoticeModel.getProposalModel().getEmail();
    }

    public LocalDateTime getCreationMomen() {
        return creationMomen;
    }

    public String getDestiny() {
        return destiny;
    }

    public LocalDate getTripEndDate() {
        return tripEndDate;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public String getIpClient() {
        return ipClient;
    }

    public String getEmailProposal() {
        return emailProposal;
    }
}
