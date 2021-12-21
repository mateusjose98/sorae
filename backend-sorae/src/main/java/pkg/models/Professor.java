package pkg.models;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Professor {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Atributos
    private String nome;
    private String idprofessor;
    private String email;
    private String telefone;
    private String ocupacao;
    private Boolean ativo = true;

    //Construtor
public Professor(String nome, String idprofessor, String email, String telefone, String ocupacao) {
    this.nome = nome;
    this.idprofessor = idprofessor;
    this.email = email;
    this.telefone = telefone;
    this.ocupacao = ocupacao;
}

public Professor(){

}

    //Métodos GET e SET
public String getNome(){
    return nome;
}
public void setNome(String nome){
    this.nome = nome;
}

public String getIdprofessor(){
    return idprofessor;
}

public void setIdprofessor(String idprofessor){
    this.idprofessor = idprofessor;
}

public String getEmail(){
    return email;
}
public void setEmail(String email){
    this.email = email;
}

public String getTelefone(){
    return telefone;
}
public void setTelefone(String telefone){
    this.telefone = telefone;
}

public String getOcupacao(){
    return ocupacao;
}
public void setOcupacao(String ocupacao){
    this.ocupacao = ocupacao;
}

@Override
public String toString(){
return "Nome do Professor: " + this.getNome() + 
"\n Área de Atuação: " + this.getOcupacao();
}

}
