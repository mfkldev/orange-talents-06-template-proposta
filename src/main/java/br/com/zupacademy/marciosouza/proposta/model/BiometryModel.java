package br.com.zupacademy.marciosouza.proposta.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
@Table(name = "biometry")
public class BiometryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String biometria;

    @ManyToOne
    private ProposalModel proposalModel;

    public BiometryModel(String biometria, ProposalModel proposalModel) {
        this.biometria = biometria;
        this.proposalModel = proposalModel;
    }

    @Deprecated
    public BiometryModel() {
    }

    public String getBiometria() {
        return biometria;
    }

    public ProposalModel getProposal() {
        return proposalModel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BiometryModel biometryModel = (BiometryModel) o;
        return Objects.equals(id, biometryModel.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}