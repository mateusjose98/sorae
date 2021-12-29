package pkg.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pkg.entities.Turma;
import pkg.entities.projections.TurmaResumoDTO;


public interface TurmaRepository extends JpaRepository<Turma, Long> {
	
	@Query(value = "select disciplina.codigo, disciplina.nome, turma_disciplina.sala,turma_disciplina.horario  "
			+ " from turma_disciplina "
			+ " inner join disciplina on disciplina.codigo = turma_disciplina.fk_disciplina "
			+ " inner join turma on turma.codigo = turma_disciplina.fk_turma "
			+ " where  turma.codigo in (select turma_id from turma_aluno where aluno_id = ?1)", nativeQuery = true)
	List<TurmaResumoDTO> buscarDisciplinasDoAluno(Long idAluno);
}
