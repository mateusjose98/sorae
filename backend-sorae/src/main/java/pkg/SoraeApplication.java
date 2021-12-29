package pkg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import pkg.entities.Aluno;
import pkg.entities.Professor;
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
			usuario.setNiveis(niveis);
			usuario.setTipo("PE");
			
			List<NivelEnum> niveisAluno = new ArrayList<>();
			niveisAluno.add(NivelEnum.ALUNO);

			Aluno aluno = new Aluno();
			aluno.setNome("Fernanda Batalha");
			aluno.setUsername("aluno");
			aluno.setPassword(passwordEncoder.encode("aluno"));
			aluno.setEmail("aluno@email.com");
			aluno.setNiveis(niveisAluno);
			aluno.setTipo("AL");
			aluno.setMatricula("20201923120");
			aluno.setAtivo(true);
			aluno.setNomeResponsavel("Raíssa da Costa");
			aluno.setTelefone("9899882222");
			
			
			
			
			
			List<NivelEnum> niveisProfessor = new ArrayList<>();
			niveisProfessor.add(NivelEnum.PROFESSOR);

			Professor professor = new Professor();
			professor.setNome("Professor Pardal");
			professor.setUsername("professor");
			professor.setPassword(passwordEncoder.encode("professor"));
			professor.setEmail("professor@email.com");
			professor.setNiveis(niveisProfessor);
			professor.setTipo("PR");
			professor.setAreaDeAtuacao("EXATAS");
			professor.setCoordenador(false);

			repository.saveAll(Arrays.asList(usuario, professor, aluno));
		}
	}
}
