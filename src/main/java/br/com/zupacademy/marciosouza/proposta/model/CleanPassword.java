package br.com.zupacademy.marciosouza.proposta.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

import javax.validation.constraints.NotBlank;

public class CleanPassword {

    @NotBlank @Length(min = 6)
    private String cleanPassword;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public CleanPassword(@NotBlank @Length(min = 6) String cleanPassword) {
        Assert.hasLength(cleanPassword, "Não pode ser em branco");
        Assert.isTrue(cleanPassword.length() >= 6, "Deve ter pelo menos 6 caracteres");

        this.cleanPassword = cleanPassword;
    }

    public String passwordEncoder(){
        return  new BCryptPasswordEncoder().encode(cleanPassword);
    }

    public String getCleanPassword() {
        return cleanPassword;
    }
}
