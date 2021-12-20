package pkg.models;



public class Professor {

    //Atributos
private String nome;
private String email;
private String telefone;
private String ocupacao;

    //Construtor
public Professor(String nome, String email, String telefone, String ocupacao) {
    this.nome = nome;
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
