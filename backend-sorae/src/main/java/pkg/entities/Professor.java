package pkg.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import lombok.Data;

@Entity @Data
public class Professor extends Usuario{

	private static final long serialVersionUID = 1L;
	private String telefone;
	private String areaDeAtuacao;
	private Boolean coordenador;
	private String ocupacao;
	
	@ManyToMany(mappedBy = "professores")
	private List<Turma> turmas;
	

	
	
	
	

}
