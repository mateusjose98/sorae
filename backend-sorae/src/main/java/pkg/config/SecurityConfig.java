package pkg.config;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import pkg.services.UsuarioService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // INJEÇÃO DO PROVEDOR DE USUÁRIOS
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtService jwtService;

    //JWT INJEÇÃO DO FILTRO
    @Bean
    public OncePerRequestFilter jwtFilter() {
        return new JwtAuthFiltro(jwtService, usuarioService);
    }

    // CRIPTOGRAFIA DE SENHAS
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // CONFIGURAÇÕES DE AUTENTICAÇÃO (1)
    /*
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // EM MEMÓRIA

        auth
                .inMemoryAuthentication()
                .passwordEncoder(passwordEncoder())
                .withUser("JOSE")
                .password(passwordEncoder().encode("123"))
                .roles("OPERADOR")
                .and()
                .passwordEncoder(passwordEncoder())
                .withUser("MARIA")
                .password(passwordEncoder().encode("000"))
                .roles("ADMIN")

        auth
                .userDetailsService(usuarioService)
                .passwordEncoder(passwordEncoder())
        ;
    }*/
    
    public String[] PUBLIC = { "/api/auth/**", "/h2-console/**", "/api/public/**" };
    public String[] ADMIN = { "/atividades/**" };

    // CONFIGURAÇÕES DE AUTORIZAÇÃO (2)
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();
        http
                .csrf().disable()  // DESABILITANDO A PROTEÇÃO CSRF pois apenas é necessário quando você tem envios de formulários da web que são propensos a "solicitações entre sites" nas outras guias do mesmo navegador
                .authorizeRequests() // INÍCIO DA CONFIG
                .antMatchers(ADMIN).hasRole("ADMIN")
                .antMatchers(PUBLIC).permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore( jwtFilter(), UsernamePasswordAuthenticationFilter.class); // EXECUTA NOSSO FILTRO ANTES DO PADRÃO SPRING
                ;



    }
}
