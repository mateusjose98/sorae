package pkg.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Entity
@Data
public class Turma {

	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	private String descricao;
	
	private Integer ano;
	
	@ManyToMany @JsonBackReference
	@JoinTable(name = "turma_professor", 
	   joinColumns = @JoinColumn(name = "turma_id"), 
	   inverseJoinColumns = @JoinColumn(name = "professor_id"))
	private List<Professor> professores;
	
	 @ManyToMany(mappedBy = "turmas")
	 private List<Aluno> alunos = new ArrayList<>();
	
}
