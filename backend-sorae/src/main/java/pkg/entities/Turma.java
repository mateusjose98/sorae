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
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Entity
@Data
public class Turma {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	private String descricao;

	private Integer ano;

	@ManyToMany
	@JoinTable(name = "turma_professor", joinColumns = @JoinColumn(name = "turma_id"), inverseJoinColumns = @JoinColumn(name = "professor_id"))
	private List<Professor> professores = new ArrayList<>();

	@ManyToMany
	@JoinTable(name = "turma_aluno", joinColumns = @JoinColumn(name = "turma_id"), inverseJoinColumns = @JoinColumn(name = "aluno_id"))
	private List<Aluno> alunos = new ArrayList<>();

	public void addAluno(Aluno aluno) {
		this.alunos.add(aluno);
		aluno.getTurmas().add(this);
	}

	public void removeAluno(Aluno aluno) {
		this.alunos.remove(aluno);
		aluno.getTurmas().remove(this);
	}

	public void addProfessor(Professor professor) {
		this.professores.add(professor);
		professor.getTurmas().add(this);
	}

	public void removeProfessor(Professor professor) {
		this.professores.remove(professor);
		professor.getTurmas().remove(this);
	}

}
