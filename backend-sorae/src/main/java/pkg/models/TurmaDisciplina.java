package pkg.models;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class TurmaDisciplina {

	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String sala;
	
	private LocalDateTime horario;

	@ManyToOne
	@JoinColumn(name = "fk_turma")
	private Turma turma;
	
	@ManyToOne
	@JoinColumn(name = "fk_disciplina")
	private Disciplina disciplina;
}
