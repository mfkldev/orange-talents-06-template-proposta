package br.com.zupacademy.marciosouza.proposta.model;

import br.com.zupacademy.marciosouza.proposta.controller.dto.TravelNoticeRequest;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "travelnotice")
public class TravelNoticeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm", shape = JsonFormat.Shape.STRING)
    private final LocalDateTime creationMoment = LocalDateTime.now();

    @NotEmpty
    private String destiny;
    @Future @NotNull @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate tripEndDate;
    private String userAgent;
    private String ipClient;

    @ManyToOne
    private ProposalModel proposalModel;

    public TravelNoticeModel(TravelNoticeRequest travelNoticeRequest, String ipClient, String userAgent, ProposalModel proposalModel) {
        this.destiny = travelNoticeRequest.getDestiny();
        this.tripEndDate = travelNoticeRequest.getTripEndDate();
        this.userAgent = userAgent;
        this.ipClient = ipClient;
        this.proposalModel = proposalModel;
    }

    @Deprecated
    public TravelNoticeModel() {
    }

    public LocalDateTime getCreationMoment() {
        return creationMoment;
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

    public ProposalModel getProposalModel() {
        return proposalModel;
    }
}
