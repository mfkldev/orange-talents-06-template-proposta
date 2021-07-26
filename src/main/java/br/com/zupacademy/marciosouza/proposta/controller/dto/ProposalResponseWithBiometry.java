package br.com.zupacademy.marciosouza.proposta.controller.dto;

import br.com.zupacademy.marciosouza.proposta.model.Biometry;
import br.com.zupacademy.marciosouza.proposta.model.Proposal;

import java.util.Set;

public class ProposalResponseWithBiometry {

    private String document;
    private String email;
    private String name;
    private String address;
    private String salary;
    private String creditCard;
    private StatusBiometry statusBiometry;

    public ProposalResponseWithBiometry(Proposal proposal) {
        this.document = hideDocumentInformation(proposal.getDocument());
        this.email = proposal.getEmail();
        this.name = proposal.getName();
        this.address = proposal.getAddress();
        this.salary = "Informação pessoal";
        this.creditCard =  hasCreditCard(proposal.getIdCard());
        this.statusBiometry = hasBiometry(proposal.getBiometryList());
    }

    private StatusBiometry hasBiometry(Set<Biometry> biometryList) {
        return biometryList.isEmpty() ? StatusBiometry.NOT_OK : StatusBiometry.OK;
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

    public String getCreditCard() {
        return creditCard;
    }

    public StatusBiometry getStatusBiometry() {
        return statusBiometry;
    }
}
