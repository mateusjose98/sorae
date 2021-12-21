package pkg.models;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Data
@Entity
public class Aluno {


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long matricula;

    private String nome;

    private String email;

    private String nomeResponsavel;

    private String telefone;

    private Boolean ativo = true;



}
