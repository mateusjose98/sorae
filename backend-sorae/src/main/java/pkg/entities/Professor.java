package pkg.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity  @DiscriminatorValue("PR")
public class Professor extends Usuario{

	private static final long serialVersionUID = 1L;
	private String telefone;
	private String areaDeAtuacao;

	
	@JsonIgnore
	@ManyToMany(mappedBy = "professores")
	private List<Turma> turmas = new ArrayList<>();

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getAreaDeAtuacao() {
		return areaDeAtuacao;
	}

	public void setAreaDeAtuacao(String areaDeAtuacao) {
		this.areaDeAtuacao = areaDeAtuacao;
	}


	public List<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

	
	
	
	

}
