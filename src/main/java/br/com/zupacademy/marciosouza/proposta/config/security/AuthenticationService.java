package br.com.zupacademy.marciosouza.proposta.config.security;

import br.com.zupacademy.marciosouza.proposta.model.User;
import br.com.zupacademy.marciosouza.proposta.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService implements UserDetailsService {
	
	@Autowired
	private UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = repository.findByEmail(username);
		if (user.isPresent()) {
			return user.get();
		}
		
		throw new UsernameNotFoundException("Dados inválidos!");
	}
}
