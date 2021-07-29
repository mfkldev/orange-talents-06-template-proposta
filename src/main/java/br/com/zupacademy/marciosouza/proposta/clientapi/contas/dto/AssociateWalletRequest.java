package br.com.zupacademy.marciosouza.proposta.clientapi.contas.dto;

public class AssociateWalletRequest {

    private String email;
    private String carteira;

    public AssociateWalletRequest(String email, String carteira) {
        this.email = email;
        this.carteira = carteira;
    }

    public AssociateWalletRequest() {
    }

    public String getEmail() {
        return email;
    }

    public String getCarteira() {
        return carteira;
    }
}
