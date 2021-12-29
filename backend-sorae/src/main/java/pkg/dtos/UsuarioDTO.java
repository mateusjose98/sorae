package pkg.dtos;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;
import pkg.enums.NivelEnum;

@Data
public class UsuarioDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "*Campo 'Nome', obrigatório.")
	private String nome;
	
	@NotEmpty(message = "*Campo 'Usuario', obrigatório.")
	private String username;
	
	@NotEmpty(message = "*Campo 'Password', obrigatório.")
	private String password;
	
	 @Column(insertable=false, updatable=false)
	 private String tipo;

	@ElementCollection
	@JoinTable(name = "nivel_usuario")
	@Enumerated(EnumType.STRING)
	@NotNull(message = "Campo 'Níveis' obrigatório!")
	private List<NivelEnum> niveis;
	
	@Email
	private String email;
	private String telefone;
	private String matricula;
    private String nomeResponsavel;
	private String areaDeAtuacao;
    private String serie;
	
}
