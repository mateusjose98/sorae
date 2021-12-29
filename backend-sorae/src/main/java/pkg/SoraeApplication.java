package pkg;

import java.time.LocalDateTime;
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
import pkg.entities.Disciplina;
import pkg.entities.Professor;
import pkg.entities.Turma;
import pkg.entities.TurmaDisciplina;
import pkg.entities.Usuario;
import pkg.enums.NivelEnum;
import pkg.repositories.DisciplinaRepository;
import pkg.repositories.TurmaDisciplinaRepository;
import pkg.repositories.TurmaRepository;
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
	CommandLineRunner init(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, DisciplinaRepository discRepository, 
			TurmaRepository turmaRepository,  TurmaDisciplinaRepository turmaDisciplinaRepository) {
		return args -> {
			iniciarUsuario(usuarioRepository, passwordEncoder, discRepository, turmaRepository, turmaDisciplinaRepository);
		};
	}

	public void iniciarUsuario(UsuarioRepository repository, PasswordEncoder passwordEncoder, DisciplinaRepository discRepository, 
			TurmaRepository turmaRepository, TurmaDisciplinaRepository turmaDisciplinaRepository) {
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
			aluno.setSerie("9 ANO");

			
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


			repository.saveAll(Arrays.asList(usuario,aluno, professor ));
			
			
			Disciplina d1 = new Disciplina();
			d1.setCargaHoraria(10);
			d1.setDescricao("A melhor disciplina de todas");
			d1.setNome("Matemática");
			
			Disciplina d2 = new Disciplina();
			d2.setCargaHoraria(8);
			d2.setDescricao("A 2 melhor disciplina de todas");
			d2.setNome("Portugês");
			
			discRepository.saveAll(Arrays.asList(d1, d2));
			
			Turma t1 = new Turma();
			
			t1.setAno(2020);
			t1.setDescricao("9-ANO-C");
			t1.setAlunos(Arrays.asList(aluno));
			t1.setProfessores(Arrays.asList(professor));
			
			
			turmaRepository.save(t1);
			
			
			TurmaDisciplina td = new TurmaDisciplina();
			td.setDisciplina(d1);
			td.setHorario(LocalDateTime.of(2020, 01, 01, 10, 10));
			td.setTurma(t1);
			td.setSala("202-A");
			
			TurmaDisciplina td2 = new TurmaDisciplina();
			td2.setDisciplina(d2);
			td2.setHorario(LocalDateTime.of(2020, 01, 01, 9, 10));
			td2.setTurma(t1);
			td2.setSala("203-A");
			
			
			turmaDisciplinaRepository.saveAll(Arrays.asList(td, td2));
			
			
			
			
			
			
			
			
			
			
			
		}
	}
}
