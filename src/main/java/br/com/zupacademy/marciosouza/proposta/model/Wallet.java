package br.com.zupacademy.marciosouza.proposta.model;

import br.com.zupacademy.marciosouza.proposta.clientapi.contas.dto.Carteira;
import br.com.zupacademy.marciosouza.proposta.clientapi.contas.feignclient.AccountApi;
import br.com.zupacademy.marciosouza.proposta.config.exception.UnprocessableEntityException;
import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ProposalModel proposalModel;

    private String walletType;

    private String idWalletContasAPI;

    public Wallet(ProposalModel proposalModel, String walletType, String idWalletContasAPI) {
        this.proposalModel = proposalModel;
        this.walletType = walletType;
        this.idWalletContasAPI = idWalletContasAPI;
    }

    public Wallet() {
    }

    public ProposalModel getProposalModel() {
        return proposalModel;
    }

    public String getIdWalletContasAPI() {
        return idWalletContasAPI;
    }

    public Long getId() {
        return id;
    }

    public String getWalletType() {
        return walletType;
    }
}
