package pkg.entities;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity  @DiscriminatorValue("PR")
public class Professor extends Usuario{

	private static final long serialVersionUID = 1L;
	private String telefone;
	private String areaDeAtuacao;
	private Boolean coordenador;
	private String ocupacao;
	
	@JsonManagedReference
	@ManyToMany(mappedBy = "professores", fetch = FetchType.LAZY)
	private List<Turma> turmas;

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

	public Boolean getCoordenador() {
		return coordenador;
	}

	public void setCoordenador(Boolean coordenador) {
		this.coordenador = coordenador;
	}

	public String getOcupacao() {
		return ocupacao;
	}

	public void setOcupacao(String ocupacao) {
		this.ocupacao = ocupacao;
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
