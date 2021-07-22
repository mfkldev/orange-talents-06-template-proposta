package br.com.zupacademy.marciosouza.proposta.controller.dto;

import br.com.zupacademy.marciosouza.proposta.model.User;
import com.fasterxml.jackson.annotation.JsonCreator;

public class UserResponse {


    private String email;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public UserResponse(User user) {
        this.email = user.getEmail();
    }

    public String getEmail() {
        return email;
    }

    public String fakeUserName(){ //POG? Se for, me avisem
        return this.email.split("@")[0];
    }
}
