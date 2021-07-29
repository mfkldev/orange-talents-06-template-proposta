package br.com.zupacademy.marciosouza.proposta.clientapi.contas.dto;

public class Carteira {

    private String emissor;
    private String id;

    public Carteira(String emissor, String id) {
        this.emissor = emissor;
        this.id = id;
    }

    public Carteira() {}

    public String getEmissor() {
        return emissor;
    }

    public String getId() {
        return id;
    }

    public boolean hasWallet(String walletType){
        if (this.emissor.equals(walletType)){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Carteira{" +
                "emissor='" + emissor + '\'' +
                '}';
    }
}
