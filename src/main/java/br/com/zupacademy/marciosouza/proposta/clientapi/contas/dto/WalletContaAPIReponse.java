package br.com.zupacademy.marciosouza.proposta.clientapi.contas.dto;

public class WalletContaAPIReponse {

    private String resultado;
    private String id;

    public WalletContaAPIReponse(String resultado, String id) {
        this.resultado = resultado;
        this.id = id;
    }

    public String getResultado() {
        return resultado;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "WalltetContaAPIReponse{" +
                "resultado='" + resultado + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
