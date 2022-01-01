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
import pkg.entities.Atividade;
import pkg.entities.Disciplina;
import pkg.entities.Professor;
import pkg.entities.Turma;
import pkg.entities.TurmaDisciplina;
import pkg.entities.Usuario;
import pkg.enums.NivelEnum;
import pkg.repositories.AtividadeRepository;
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
	CommandLineRunner init(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder,
			DisciplinaRepository discRepository, TurmaRepository turmaRepository,
			TurmaDisciplinaRepository turmaDisciplinaRepository, AtividadeRepository atividadeRepository) {
		return args -> {
			iniciarUsuario(usuarioRepository, passwordEncoder, discRepository, turmaRepository,
					turmaDisciplinaRepository, atividadeRepository);
		};
	}

	public void iniciarUsuario(UsuarioRepository repository, PasswordEncoder passwordEncoder,
			DisciplinaRepository discRepository, TurmaRepository turmaRepository,
			TurmaDisciplinaRepository turmaDisciplinaRepository, AtividadeRepository atividadeRepository) {
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
			professor.setNome("Professor professor da Silva");
			professor.setUsername("professor");
			professor.setPassword(passwordEncoder.encode("professor"));
			professor.setEmail("professor@email.com");
			professor.setNiveis(niveisProfessor);
			professor.setTipo("PR");
			professor.setAreaDeAtuacao("EXATAS");


			repository.saveAll(Arrays.asList(usuario, aluno, professor));
			
			
			Turma t1 = new Turma();

			t1.setAno(2020);
			t1.setDescricao("9-ANO-C");
			// ALUNO E PROFESSOR NA TURMA
			t1.addAluno(aluno);
			t1.addProfessor(professor);

			turmaRepository.save(t1);
			


			Disciplina d1 = new Disciplina();
			d1.setCargaHoraria(10);
			d1.setDescricao("A melhor disciplina de todas");
			d1.setNome("Matemática");

			Disciplina d2 = new Disciplina();
			d2.setCargaHoraria(8);
			d2.setDescricao("A 2 melhor disciplina de todas");
			d2.setNome("Portugês");

			Disciplina d3 = new Disciplina();
			d3.setCargaHoraria(1);
			d3.setDescricao("A melhor disciplina de todas");
			d3.setNome("Ciências");

			Disciplina d4 = new Disciplina();
			d4.setCargaHoraria(2);
			d4.setDescricao("A 2 melhor disciplina de todas");
			d4.setNome("Geografia");

			Disciplina d5 = new Disciplina();
			d5.setCargaHoraria(2);
			d5.setDescricao("A 2 melhor disciplina de todas");
			d5.setNome("Química");

			Disciplina d6 = new Disciplina();
			d6.setCargaHoraria(2);
			d6.setDescricao("A 2 melhor disciplina de todas");
			d6.setNome("Filosofia");

			Disciplina d7 = new Disciplina();
			d7.setCargaHoraria(2);
			d7.setDescricao("A 2 melhor disciplina de todas");
			d7.setNome("Ed. Física");

			Disciplina d8 = new Disciplina();
			d8.setCargaHoraria(2);
			d8.setDescricao("A 2 melhor disciplina de todas");
			d8.setNome("Artes");

			discRepository.saveAll(Arrays.asList(d1, d2, d3, d4, d5, d6, d7, d8));

			TurmaDisciplina td = new TurmaDisciplina();
			td.setDisciplina(d1);
			td.setHorario(LocalDateTime.of(2020, 01, 01, 10, 10));
			td.setTurma(t1);
			td.setSala("202-A");
			td.setProfessor(professor);

			TurmaDisciplina td2 = new TurmaDisciplina();
			td2.setDisciplina(d2);
			td2.setHorario(LocalDateTime.of(2020, 01, 01, 9, 10));
			td2.setTurma(t1);
			td2.setSala("203-A");

			TurmaDisciplina td3 = new TurmaDisciplina();
			td3.setDisciplina(d3);
			td3.setHorario(LocalDateTime.of(2020, 01, 01, 10, 10));
			td3.setTurma(t1);
			td3.setSala("202-A");

			TurmaDisciplina td4 = new TurmaDisciplina();
			td4.setDisciplina(d4);
			td4.setHorario(LocalDateTime.of(2020, 02, 01, 19, 10));
			td4.setTurma(t1);
			td4.setSala("202-A");

			TurmaDisciplina td5 = new TurmaDisciplina();
			td5.setDisciplina(d5);
			td5.setHorario(LocalDateTime.of(2020, 01, 01, 10, 10));
			td5.setTurma(t1);
			td5.setSala("202-A");

			TurmaDisciplina td6 = new TurmaDisciplina();
			td6.setDisciplina(d6);
			td6.setHorario(LocalDateTime.of(2020, 01, 01, 19, 10));
			td6.setTurma(t1);
			td6.setSala("202-A");

			TurmaDisciplina td7 = new TurmaDisciplina();
			td7.setDisciplina(d7);
			td7.setHorario(LocalDateTime.of(2020, 01, 01, 03, 10));
			td7.setTurma(t1);
			td7.setSala("202-A");

			TurmaDisciplina td8 = new TurmaDisciplina();
			td8.setDisciplina(d8);
			td8.setHorario(LocalDateTime.of(2020, 01, 01, 10, 10));
			td8.setTurma(t1);
			td8.setSala("202-A");

			turmaDisciplinaRepository.saveAll(Arrays.asList(td, td2, td3, td4, td5, td6, td7, td8));

			Atividade a1 = new Atividade();
			Atividade a2 = new Atividade();
			Atividade a3 = new Atividade();
			Atividade a4 = new Atividade();
			Atividade a5 = new Atividade();

			a1.setTitulo("Atividade Av. 1");
			a2.setTitulo("Atividade Av. 2");
			a3.setTitulo("Atividade Av. 4");
			a4.setTitulo("Ativ. Extra 1");
			a5.setTitulo("Atividade Av. 1");

			a1.setDataEntrega(LocalDateTime.of(2020, 01, 07, 11, 10));
			a2.setDataEntrega(LocalDateTime.of(2020, 01, 06, 14, 10));
			a3.setDataEntrega(LocalDateTime.of(2020, 01, 04, 15, 07));
			a4.setDataEntrega(LocalDateTime.of(2020, 01, 02, 19, 00));
			a5.setDataEntrega(LocalDateTime.of(2020, 01, 01, 10, 10));

			a1.setDataPostagem(LocalDateTime.of(2020, 01, 01, 11, 10));
			a2.setDataPostagem(LocalDateTime.of(2020, 01, 01, 12, 10));
			a3.setDataPostagem(LocalDateTime.of(2020, 01, 01, 15, 10));
			a4.setDataPostagem(LocalDateTime.of(2020, 01, 01, 7, 10));
			a5.setDataPostagem(LocalDateTime.of(2020, 01, 01, 10, 10));

			a1.setDescricao("Uma atividade para agregar na nota 1");
			a2.setDescricao("Faça uma Redação sobre ...");
			a3.setDescricao("Entregar o relatório sobre a feira de ciências");
			a4.setDescricao("Atividade avaliativa 3");
			a5.setDescricao("Atividade avaliativa 2");

			a1.setTipo("AV");
			a2.setTipo("AV");
			a3.setTipo("AV");
			a4.setTipo("AV");
			a5.setTipo("AV");

			a1.setTurmasDisciplina(td);
			a2.setTurmasDisciplina(td);
			a3.setTurmasDisciplina(td);
			a4.setTurmasDisciplina(td2);
			a5.setTurmasDisciplina(td2);

			atividadeRepository.saveAll(Arrays.asList(a1, a2, a3, a4, a5));

		}
	}
}
