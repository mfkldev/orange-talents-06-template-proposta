package br.com.zupacademy.marciosouza.proposta.model;

import br.com.zupacademy.marciosouza.proposta.config.validation.CpfCnpj;
import br.com.zupacademy.marciosouza.proposta.controller.usecase.CryptDocumentUsecase;
import br.com.zupacademy.marciosouza.proposta.model.enums.StatusProposal;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.*;

@Entity
@Table(name = "proposal")
public class ProposalModel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String document;

    @Email @NotBlank
    private String email;

    @NotBlank
    private String name;

    @NotBlank
    private String address;

    @Positive @NotNull
    private BigDecimal salary;

    @Enumerated(EnumType.STRING)
    private StatusProposal status;

    @Column(name = "idcard")
    private String idCard = null;

    @OneToMany(mappedBy = "proposalModel", cascade = CascadeType.MERGE)
    private Set<BiometryModel> biometryModelList = new HashSet<>();

    @OneToMany(mappedBy = "proposalModel")
    private List<CardlockModel> cardlockModelList = new ArrayList<>();

    @OneToMany(mappedBy = "proposalModel")
    private List<TravelNoticeModel> travelNoticeModelList = new ArrayList<>();

    @OneToMany(mappedBy = "proposalModel")
    private List<Wallet> walletList = new ArrayList<>();

    @Deprecated
    public ProposalModel() {
    }

    public ProposalModel(String document, String email, String name, String address, BigDecimal salary) {
        this.document = CryptDocumentUsecase.encryptDocument(document);
        this.email = email;
        this.name = name;
        this.address = address;
        this.salary = salary;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public Long getId() {
        return id;
    }

    public String getDocument() {
        return CryptDocumentUsecase.dencryptDocument(document);
    }

    public void setStatus(String status) {
        this.status = StatusProposal.getStatus(status);
    }

    public StatusProposal getStatus() {
        return status;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getIdCard() {
        return this.idCard;
    }

    public Set<BiometryModel> getBiometryList() {
        return biometryModelList;
    }

    public void setBiometry(BiometryModel biometryModel) {
        biometryModelList.add(biometryModel);
    }
}