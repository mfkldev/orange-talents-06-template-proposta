package br.com.zupacademy.marciosouza.proposta.model;

import br.com.zupacademy.marciosouza.proposta.config.validation.CpfCnpj;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Entity
public class Proposal {

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

    @Deprecated
    public Proposal() {
    }

    public Proposal(String document, String email, String name, String address, BigDecimal salary) {
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
}