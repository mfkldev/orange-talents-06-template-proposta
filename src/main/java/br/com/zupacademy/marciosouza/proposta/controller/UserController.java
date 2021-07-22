package br.com.zupacademy.marciosouza.proposta.controller;

import br.com.zupacademy.marciosouza.proposta.controller.dto.UserRequest;
import br.com.zupacademy.marciosouza.proposta.controller.dto.UserResponse;
import br.com.zupacademy.marciosouza.proposta.model.User;
import br.com.zupacademy.marciosouza.proposta.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> save(@RequestBody @Valid UserRequest userRequest){
        User user = userRequest.converter();
        userRepository.save(user);

        return ResponseEntity.ok(new UserResponse(user));
    }
}
