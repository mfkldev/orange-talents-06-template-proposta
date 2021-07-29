package br.com.zupacademy.marciosouza.proposta.controller.dto;

import br.com.zupacademy.marciosouza.proposta.model.Wallet;

public class WalletResponse {

    private String emailProposal;

    private String walletType;

    private String idWalletContasAPI;

    public WalletResponse(Wallet wallet) {
        this.emailProposal = wallet.getProposalModel().getEmail();
        this.walletType = wallet.getWalletType();
        this.idWalletContasAPI = wallet.getIdWalletContasAPI();
    }

    public String getEmailProposal() {
        return emailProposal;
    }

    public String getWalletType() {
        return walletType;
    }

    public String getIdWalletContasAPI() {
        return idWalletContasAPI;
    }
}
