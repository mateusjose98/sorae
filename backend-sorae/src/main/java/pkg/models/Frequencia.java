package pkg.models;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Frequencia {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDate data;
	
	private Boolean presente;
	
	@ManyToOne
	@JoinColumn(name = "fk_turma_disciplina")
	private TurmaDisciplina turmaDisciplina;

	
	@ManyToOne
	@JoinColumn(name = "fk_aluno")
	private Aluno aluno;
}
