package pkg.entities;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=false)
@Entity
public class Aluno extends Usuario{
	
	private Long matricula;

    private String nome;

    private String email;

    private String nomeResponsavel;

    private String telefone;

    private Boolean ativo = true;
    
    @OneToMany(mappedBy = "aluno")
    private List<Frequencia> frequencias;
    
    @OneToMany(mappedBy = "aluno")
    private List<Atividade> atividades;



}
