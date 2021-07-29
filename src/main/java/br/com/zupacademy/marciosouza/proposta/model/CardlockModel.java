package br.com.zupacademy.marciosouza.proposta.model;

import br.com.zupacademy.marciosouza.proposta.clientapi.contas.dto.Bloqueios;
import br.com.zupacademy.marciosouza.proposta.clientapi.contas.feignclient.AccountApi;
import br.com.zupacademy.marciosouza.proposta.config.exception.CardlockUnprocessableEntityException;
import br.com.zupacademy.marciosouza.proposta.model.enums.StatusCardLock;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "cardlock")
public class CardlockModel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm", shape = JsonFormat.Shape.STRING)
    private final LocalDateTime creationMoment = LocalDateTime.now();

    @NotBlank
    private String ipClient;

    @NotBlank
    private String userAgent;

    @ManyToOne
    private ProposalModel proposalModel;

    private StatusCardLock statusCardLock;

    public CardlockModel(String ipClient, String userAgent, ProposalModel proposalModel) {
        this.ipClient = ipClient;
        this.userAgent = userAgent;
        this.proposalModel = proposalModel;
    }

    public CardlockModel() {}

    public void cardLockedVerication(String idcard, AccountApi accountApi) {
        List<Bloqueios> bloqueios = Objects.requireNonNull(accountApi.cardLockVerification(idcard).getBody()).getBloqueios();

        boolean hasActiveBlock = bloqueios.stream().anyMatch(Bloqueios::isAtivo);
        if (hasActiveBlock) throw new CardlockUnprocessableEntityException("O cartão já se encontra bloqueado");
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

    public ProposalModel getProposal() {
        return proposalModel;
    }

    public StatusCardLock getStatusCardLock() {
        return statusCardLock;
    }

    public void setStatusCardLock(StatusCardLock statusCardLock) {
        this.statusCardLock = statusCardLock;
    }
}