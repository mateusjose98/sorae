package pkg.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
	
	@OneToMany(mappedBy = "turmasDisciplina") @JsonBackReference
	private List<Atividade> atividades;
	
	@ManyToOne @JsonBackReference
	@JoinColumn(name = "professor_id")
	private Professor professor;
}
