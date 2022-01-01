package pkg.entities;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity @DiscriminatorValue("AL")
public class Aluno extends Usuario{
	
	private String matricula;


    private String nomeResponsavel;

    private String telefone;

    private Boolean ativo = true;
    
    private String serie;
    
    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL)
    private List<Frequencia> frequencias;
    
    @JsonManagedReference
    @OneToMany(mappedBy = "aluno", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<AlunoAtividade> alunoAtividades;

    
    @ManyToMany
	 @JoinTable(name = "turma_aluno", 
	   joinColumns = @JoinColumn(name = "aluno_id"), 
	   inverseJoinColumns = @JoinColumn(name = "turma_id"))
    private List<Turma> turmas = new ArrayList<>();
    
   

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}

	public List<AlunoAtividade> getAlunoAtividades() {
		return alunoAtividades;
	}

	public void setAlunoAtividades(List<AlunoAtividade> alunoAtividades) {
		this.alunoAtividades = alunoAtividades;
	}

	public List<Turma> getTurmas() {
		return turmas;
	}


	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNomeResponsavel() {
		return nomeResponsavel;
	}

	public void setNomeResponsavel(String nomeResponsavel) {
		this.nomeResponsavel = nomeResponsavel;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public List<Frequencia> getFrequencias() {
		return frequencias;
	}

	public void setFrequencias(List<Frequencia> frequencias) {
		this.frequencias = frequencias;
	}

//	public List<Atividade> getAtividades() {
//		return atividades;
//	}
//
//	public void setAtividades(List<Atividade> atividades) {
//		this.atividades = atividades;
//	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}
    
    
    
    



}
