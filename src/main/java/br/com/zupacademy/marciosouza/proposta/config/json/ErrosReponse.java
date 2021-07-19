package br.com.zupacademy.marciosouza.proposta.config.json;

public class ErrosReponse {

    private String campo;
    private String erro;

    public ErrosReponse(String campo, String erro) {
        this.campo = campo;
        this.erro = erro;
    }

    public String getCampo() {
        return campo;
    }

    public String getErro() {
        return erro;
    }
}
