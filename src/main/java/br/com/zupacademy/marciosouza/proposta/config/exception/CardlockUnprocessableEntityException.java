package br.com.zupacademy.marciosouza.proposta.config.exception;

public class CardlockUnprocessableEntityException extends RuntimeException {

    private String message;

    public CardlockUnprocessableEntityException(String message) {
        super(message);
    }
}