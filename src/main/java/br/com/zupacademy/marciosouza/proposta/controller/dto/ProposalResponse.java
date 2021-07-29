package br.com.zupacademy.marciosouza.proposta.controller.dto;

import br.com.zupacademy.marciosouza.proposta.model.ProposalModel;

public class ProposalResponse {

    private String document;
    private String email;
    private String name;
    private String address;
    private String salary;
    private String status;
    private String creditCard;

    public ProposalResponse(ProposalModel proposalModel) {
        this.document = hideDocumentInformation(proposalModel.getDocument());
        this.email = proposalModel.getEmail();
        this.name = proposalModel.getName();
        this.address = proposalModel.getAddress();
        this.salary = "Informação pessoal";
        this.status = proposalModel.getStatus().name();
        this.creditCard =  hasCreditCard(proposalModel.getIdCard());
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
