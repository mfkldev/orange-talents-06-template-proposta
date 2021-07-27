package br.com.zupacademy.marciosouza.proposta.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
@Configuration
@Profile("prod")
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests(authorizeRequests -> authorizeRequests
				.antMatchers(HttpMethod.GET, "/proposta/**").hasAuthority("SCOPE_adm")
				.antMatchers(HttpMethod.POST, "/proposta/**").hasAuthority("SCOPE_adm")
				.antMatchers(HttpMethod.POST, "/biometria/**").hasAuthority("SCOPE_adm")
				.antMatchers(HttpMethod.GET, "/actuator/**").hasAuthority("SCOPE_adm"))
				.cors()
				.and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and().csrf().disable()
				.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
	}
}