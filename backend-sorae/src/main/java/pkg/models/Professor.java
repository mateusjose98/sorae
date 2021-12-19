package pkg.models;



public class Professor {

    //Atributos
private String name;
private String email;
private String phone;
private String occupation;

    //Construtor
public Professor(String name, String email, String phone, String occupation) {
    this.name = name;
    this.email = email;
    this.phone = phone;
    this.occupation = occupation;
}

public Professor(){

}

    //Métodos GET e SET
public String getName(){
    return name;
}
public void setName(String name){
    this.name = name;
}

public String getEmail(){
    return email;
}
public void setEmail(String email){
    this.email = email;
}

public String getPhone(){
    return phone;
}
public void setPhone(String phone){
    this.phone = phone;
}

public String getOccupation(){
    return occupation;
}
public void setOccupation(String occupation){
    this.occupation = occupation;
}

@Override
public String toString(){
return "Nome do Professor: " + this.getName() + 
"\n Área de Atuação: " + this.getOccupation();
}

}
