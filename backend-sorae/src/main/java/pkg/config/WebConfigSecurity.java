package pkg.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import pkg.filter.JwtTokenFilterConfigurer;
import pkg.services.JwtTokenService;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebConfigSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtTokenService jwtTokenService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//    	http.httpBasic()
//    		.and()
//	    		.authorizeRequests()
//	    			.antMatchers("/api/v1/csv/dependentes/").authenticated()
//	    			.anyRequest().permitAll()
//			.and()
//				.sessionManagement()
//					.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//    		.and()
//	    		.csrf()
//	    			.disable();
		// Disable CSRF (cross site request forgery)
		http.cors().and().csrf().disable();

		// No session will be created or used by spring security
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		// Entry points
		http.authorizeRequests()
				.antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources", "/configuration/security",
						"/swagger-ui.html", "/webjars/**", "/swagger-resources/configuration/ui", "/swagger-ui.html",
						"/auth/**")
				.permitAll()
				// TODO configurar as permissoes para rotas

//				.antMatchers(HttpMethod.POST, "/api/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_USUARIO")
//				.antMatchers("/admin/**")
//					.hasAuthority("ROLE_ADMIN")
				.antMatchers(HttpMethod.GET, "/api/**")
					.hasAnyAuthority("ROLE_ADMIN", "ROLE_CONSULTA")
				.antMatchers(HttpMethod.GET, "/atividades/**")
					.hasAnyAuthority("ROLE_ADMIN", "ROLE_CONSULTA");

		// If a user try to access a resource without having enough permissions
		http.exceptionHandling().accessDeniedPage("/login");

		// Apply JWT
		http.apply(new JwtTokenFilterConfigurer(jwtTokenService));
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager customAuthenticationManager() throws Exception {
		return authenticationManager();
	}

}
