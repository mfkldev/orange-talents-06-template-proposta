package br.com.zupacademy.marciosouza.proposta.config.exception;

public class ProposalNotFoundException extends RuntimeException{

    private String message;

    public ProposalNotFoundException(String message) {
        super(message);
    }
}
