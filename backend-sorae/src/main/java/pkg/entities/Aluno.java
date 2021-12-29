package pkg.entities;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Entity @Data
public class Aluno extends Usuario{
	
	private Long matricula;


    private String nomeResponsavel;

    private String telefone;

    private Boolean ativo = true;
    
    @OneToMany(mappedBy = "aluno")
    private List<Frequencia> frequencias;
    
    @OneToMany(mappedBy = "aluno")
    private List<Atividade> atividades;
    
    
    
    



}
