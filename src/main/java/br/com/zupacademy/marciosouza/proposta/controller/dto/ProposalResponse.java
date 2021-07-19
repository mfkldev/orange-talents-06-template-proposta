package br.com.zupacademy.marciosouza.proposta.controller.dto;

import br.com.zupacademy.marciosouza.proposta.model.Proposal;

public class ProposalResponse {

    private String document;
    private String email;
    private String name;
    private String address;
    private String salary;

    public ProposalResponse(Proposal proposal) {
        this.document = "***.***.***.**";
        this.email = proposal.getEmail();
        this.name = proposal.getName();
        this.address = proposal.getAddress();
        this.salary = "**.**";
    }

    public String getDocument() {
        return document;
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

    public String getSalary() {
        return salary;
    }
}
