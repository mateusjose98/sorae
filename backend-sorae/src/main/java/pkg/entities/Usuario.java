package pkg.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinTable;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import pkg.enums.NivelEnum;

@Entity
@Table(name = "usuario")@Inheritance(strategy = InheritanceType.SINGLE_TABLE) @DiscriminatorColumn(name = "tipo", length = 2, 
discriminatorType = DiscriminatorType.STRING) @DiscriminatorValue("US")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty(message = "*Campo 'Nome', obrigatório.")
	private String nome;
	//Username
	@NotEmpty(message = "*Campo 'Usuario', obrigatório.")
	private String username;
	
	@NotEmpty(message = "*Campo 'Password', obrigatório.")
	private String password;
	
	 @Column(insertable=false, updatable=false)
	    private String tipo;
	
	
//	//Mesmo que status
//	private boolean ativo = true;
//	private boolean habilitado = true;
//	private boolean expirado = false;
//	private boolean bloqueado = false;
	@ElementCollection
	@JoinTable(name = "nivel_usuario")
	@Enumerated(EnumType.STRING)
	@NotNull(message = "Campo 'Níveis' obrigatório!")
	private List<NivelEnum> niveis;
	
	@Email
	private String email;
	
	private String telefone;
	
	private String newPassword;

	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
		
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<NivelEnum> getNiveis() {
		return niveis;
	}

	public void setNiveis(List<NivelEnum> niveis) {
		this.niveis = niveis;
	}
	
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
	
	
	public String getNewPassword() {
		return newPassword;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
