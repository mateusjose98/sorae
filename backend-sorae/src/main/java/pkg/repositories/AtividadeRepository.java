package pkg.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pkg.entities.Atividade;
import pkg.entities.projections.AtividadeDetalhe;

public interface AtividadeRepository extends JpaRepository<Atividade, Long> {

	@Query(value = "select u.id as idProfessor, u.nome as nomeProfessor, a.titulo as titulo, "
			+ "a.descricao as descricaoAtividade,  d.nome as disciplina,    "
			+ "TO_CHAR(a.DATA_POSTAGEM, 'DD/MM/YYYY HH24hMI') AS DATAPOSTAGEM, "
			+ "TO_CHAR(a.DATA_ENTREGA, 'DD/MM/YYYY HH24hMI') AS DATAENTREGA  " + "from atividade a "
			+ "join turma_disciplina td on td.id = a.turma_disciplina_id   "
			+ "join disciplina d on d.id = td.fk_disciplina " + "left join usuario u on u.id = td.professor_id  "
			+ "where a.codigo = ?1 ", nativeQuery = true)
	AtividadeDetalhe buscarAtividadeDetalhadaPorId(Long idAtividade);

}
