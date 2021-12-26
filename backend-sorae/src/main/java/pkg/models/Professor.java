package pkg.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.Data;

@Entity 
public class Professor extends Usuario{

	private static final long serialVersionUID = 1L;
	private String email;
	private String telefone;
	private String areaDeAtuacao;
	private Boolean coordenador;
	private String ocupacao;

}
