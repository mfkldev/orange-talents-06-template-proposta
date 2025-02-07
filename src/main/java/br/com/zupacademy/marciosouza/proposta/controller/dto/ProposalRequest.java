package br.com.zupacademy.marciosouza.proposta.controller.dto;

import br.com.zupacademy.marciosouza.proposta.config.validation.CpfCnpj;
import br.com.zupacademy.marciosouza.proposta.config.validation.Unique;
import br.com.zupacademy.marciosouza.proposta.model.ProposalModel;
import javax.validation.constraints.*;
import java.math.BigDecimal;

public class ProposalRequest {

    @CpfCnpj @NotBlank
    private String document;

    @Email @NotBlank @Unique(fieldName = "email", clazz = ProposalModel.class)
    private String email;

    @NotBlank
    private String name;

    @NotBlank
    private String address;

    @Positive @NotNull
    private BigDecimal salary;

    public ProposalRequest(String document, String email, String name, String address, BigDecimal salary) {
        this.document = document;
        this.email = email;
        this.name = name;
        this.address = address;
        this.salary = salary;
    }

    public ProposalModel toModel() {
        return new ProposalModel(this.document, this.email, this.name, this.address, this.salary);
    }

    public String getEmail() {
        return email;
    }

    public String getDocument() {
        return document;
    }
}
