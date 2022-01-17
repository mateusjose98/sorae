package pkg.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity  @DiscriminatorValue("PR")
public class Professor extends Usuario{

	private static final long serialVersionUID = 1L;
	private String telefone;
	private String areaDeAtuacao;

	@OneToMany(mappedBy = "professor") @JsonManagedReference
	private List<TurmaDisciplina> turmas = new ArrayList<>();
	

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



	public List<TurmaDisciplina> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<TurmaDisciplina> turmas) {
		this.turmas = turmas;
	}
	

	
	
	
	

}
