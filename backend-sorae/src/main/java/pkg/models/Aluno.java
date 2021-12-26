package pkg.models;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;


@Data
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
