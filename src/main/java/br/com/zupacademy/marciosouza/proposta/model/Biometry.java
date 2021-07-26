package br.com.zupacademy.marciosouza.proposta.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
public class Biometry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String biometria;

    @ManyToOne
    private Proposal proposal;

    public Biometry(String biometria, Proposal proposal) {
        this.biometria = biometria;
        this.proposal = proposal;
    }

    @Deprecated
    public Biometry() {
    }

    public String getBiometria() {
        return biometria;
    }

    public Proposal getProposal() {
        return proposal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Biometry biometry = (Biometry) o;
        return Objects.equals(id, biometry.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}