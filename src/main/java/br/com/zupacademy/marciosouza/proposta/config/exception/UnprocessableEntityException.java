package br.com.zupacademy.marciosouza.proposta.config.exception;

public class UnprocessableEntityException extends RuntimeException {

    private String message;

    public UnprocessableEntityException(String message) {
        super(message);
    }
}