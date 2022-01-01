package pkg.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pkg.entities.Turma;
import pkg.entities.projections.AtividadeResumoDTO;
import pkg.entities.projections.TurmaResumoDTO;


public interface TurmaRepository extends JpaRepository<Turma, Long> {
	
	@Query(value = "select disciplina.id as codigo, disciplina.nome, turma_disciplina.sala, to_char( turma_disciplina.horario, 'HH24:MI' ) as horario "
			+ " from turma_disciplina "
			+ " inner join disciplina on disciplina.id = turma_disciplina.fk_disciplina "
			+ " inner join turma on turma.codigo = turma_disciplina.fk_turma "
			+ " where  turma.codigo in (select turma_id from turma_aluno where aluno_id = ?1)", nativeQuery = true)
	List<TurmaResumoDTO> buscarDisciplinasDoAluno(Long idAluno);
	
	
	@Query(value = "SELECT CODIGO, "
			+ "TITULO, "
			+ "TO_CHAR(DATA_ENTREGA, 'DD/MM/YYYY HH24:MI') AS DATAENTREGA , "
			+ "TO_CHAR(DATA_POSTAGEM, 'DD/MM/YYYY HH24:MI') AS DATAPOSTAGEM, "
			+ " CONCAT(substring(DESCRICAO from 0 for 25), '...') as DESCRICAO  "
			+ "FROM ATIVIDADE  "
			+ "WHERE TURMA_DISCIPLINA_ID IN "
			+ "( "
			+ "SELECT ID FROM TURMA_DISCIPLINA WHERE FK_TURMA IN "
			+ "      (SELECT TURMA_ID FROM TURMA_ALUNO WHERE ALUNO_ID = ?1) "
			+ ") ", nativeQuery = true)
	List<AtividadeResumoDTO> buscarAtividadesDaTurmaDoAluno(Long idAluno);
}
