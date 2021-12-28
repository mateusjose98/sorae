package pkg;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import pkg.entities.Usuario;
import pkg.enums.NivelEnum;
import pkg.repositories.UsuarioRepository;

@SpringBootApplication
public class SoraeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoraeApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	CommandLineRunner init(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			iniciarUsuario(usuarioRepository, passwordEncoder);
		};
	}

	public void iniciarUsuario(UsuarioRepository repository, PasswordEncoder passwordEncoder) {
		List<Usuario> usuarios = repository.findAll();

		if (usuarios.isEmpty()) {
			List<NivelEnum> niveis = new ArrayList<>();
			niveis.add(NivelEnum.ADMIN);

			Usuario usuario = new Usuario();
			usuario.setNome("Administrador");
			usuario.setUsername("admin");
			usuario.setPassword(passwordEncoder.encode("admin"));
			usuario.setEmail("email@email.com");
			usuario.setAtivo(true);
			usuario.setBloqueado(false);
			usuario.setExpirado(false);
			usuario.setHabilitado(true);
			usuario.setNiveis(niveis);

			repository.save(usuario);
		}
	}
}
