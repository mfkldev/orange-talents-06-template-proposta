package br.com.zupacademy.marciosouza.proposta.model;

import br.com.zupacademy.marciosouza.proposta.config.validation.CpfCnpj;
import br.com.zupacademy.marciosouza.proposta.model.enums.StatusProposal;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "proposal")
public class ProposalModel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CpfCnpj @NotBlank
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

    @Deprecated
    public ProposalModel() {
    }

    public ProposalModel(String document, String email, String name, String address, BigDecimal salary) {
        this.document = document;
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
        return document;
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