package br.com.zupacademy.marciosouza.proposta.controller.service;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.stream.Stream;

public class IpRequest {

    public static String getIpRequest(HttpServletRequest request) {

        return Stream.of(
                request.getHeader("x-forwarded-for"),
                request.getHeader("X_FORWARDED_FOR"),
                request.getRemoteAddr(),
                "Infelizmente não foi possível capturar o IP"
        ).filter(Objects::nonNull).findFirst().get();
    }
}
