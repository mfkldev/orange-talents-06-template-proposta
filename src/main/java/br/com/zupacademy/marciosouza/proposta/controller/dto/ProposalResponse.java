package br.com.zupacademy.marciosouza.proposta.controller.dto;

import br.com.zupacademy.marciosouza.proposta.model.Proposal;

public class ProposalResponse {

    private String document;
    private String email;
    private String name;
    private String address;
    private String salary;
    private String status;
    private String creditCard;

    public ProposalResponse(Proposal proposal) {
        this.document = hideDocumentInformation(proposal.getDocument());
        this.email = proposal.getEmail();
        this.name = proposal.getName();
        this.address = proposal.getAddress();
        this.salary = "Informação pessoal";
        this.status = proposal.getStatus().name();
        this.creditCard =  hasCreditCard(proposal.getIdCard());
    }

    public String hasCreditCard(String numberCreditCard){
        if (numberCreditCard == null){
            return "Proposta em análise :-)";
        }else{
            return hideCardInformation(numberCreditCard);
        }
    }

    public String hideCardInformation(String numberCreditCard){
        return "****.".repeat(3).concat(numberCreditCard.substring(15, 19));
    }

    public String hideDocumentInformation(String numberDocument){
        return numberDocument.substring(0, 3).concat((".***").repeat(2)).concat("-**");
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

    public String getStatus() {
        return status;
    }

    public String getCreditCard() {
        return creditCard;
    }
}
