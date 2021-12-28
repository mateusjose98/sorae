package pkg.entities;

import javax.persistence.Entity;

@Entity 
public class Professor extends Usuario{

	private static final long serialVersionUID = 1L;
	private String email;
	private String telefone;
	private String areaDeAtuacao;
	private Boolean coordenador;
	private String ocupacao;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
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
	
	
	

}
