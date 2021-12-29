package pkg.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Atividade {
	
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	private String titulo;
	private String descricao;
	private LocalDateTime dataPostagem;
	private LocalDateTime dataEntrega;
	private String tipo;
	
	
	@OneToMany(mappedBy = "atividade")  @JsonManagedReference
	private List<AlunoAtividade> alunoAtividade;
	
	
	@ManyToOne
	@JoinColumn(name = "turma_disciplina_id")
	private TurmaDisciplina turmasDisciplina;

	
	public Atividade() {
	}
	

	public Atividade(Long codigo, String titulo, String descricao, LocalDateTime dataPostagem, LocalDateTime dataEntrega,
			Double nota, String tipo) {
		super();
		this.codigo = codigo;
		this.titulo = titulo;
		this.descricao = descricao;
		this.dataPostagem = dataPostagem;
		this.dataEntrega = dataEntrega;
		this.tipo = tipo;
	}








	public List<AlunoAtividade> getAlunoAtividade() {
		return alunoAtividade;
	}


	public void setAlunoAtividade(List<AlunoAtividade> alunoAtividade) {
		this.alunoAtividade = alunoAtividade;
	}


	public TurmaDisciplina getTurmasDisciplina() {
		return turmasDisciplina;
	}


	public void setTurmasDisciplina(TurmaDisciplina turmasDisciplina) {
		this.turmasDisciplina = turmasDisciplina;
	}


	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public LocalDateTime getDataPostagem() {
		return dataPostagem;
	}
	public void setDataPostagem(LocalDateTime dataPostagem) {
		this.dataPostagem = dataPostagem;
	}
	public LocalDateTime getDataEntrega() {
		return dataEntrega;
	}
	public void setDataEntrega(LocalDateTime dataEntrega) {
		this.dataEntrega = dataEntrega;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
	
	
	

}
