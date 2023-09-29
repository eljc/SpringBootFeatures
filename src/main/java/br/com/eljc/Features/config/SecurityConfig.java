package br.com.eljc.Features.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// configuringg basic authentication
		http.authorizeHttpRequests()
		.requestMatchers("/rest/**").permitAll()
		.requestMatchers("/cashcards/**")		
		//.authenticated()
		.hasAnyRole("CARD-OWNER") // enable RBAC: Replace the .authenticated() code with this line.
		.and().csrf()
		.disable()		
		.httpBasic();
		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService testOnlyUsers(PasswordEncoder passwordEncoder) {
		User.UserBuilder users = User.builder();
		UserDetails sarah = users
				.username("sarah1")
				.password(passwordEncoder
						.encode("abc123"))
				.roles("CARD-OWNER") // new role
				.build();
		
		 UserDetails hankOwnsNoCards = users
				    .username("hank-owns-no-cards")
				    .password(passwordEncoder.encode("qrs456"))
				    .roles("NON-OWNER") // new role
				    .build();
		return new InMemoryUserDetailsManager(sarah, hankOwnsNoCards);
	}
}
