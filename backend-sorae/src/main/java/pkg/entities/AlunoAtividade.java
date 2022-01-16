package pkg.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Entity @Data
public class AlunoAtividade {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@ManyToOne @JsonBackReference
	@JoinColumn(name = "aluno_id")
	private Aluno aluno;
	
	@ManyToOne @JsonBackReference
	@JoinColumn(name = "atividade_id")
	private Atividade atividade;
	
	private Double notaDoAluno;
	
	@Column(columnDefinition = "TEXT")
	private String resposta;
	

}
