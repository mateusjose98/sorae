package pkg.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import pkg.entities.AlunoAtividade;

@Repository
public interface AlunoAtividadeRepository extends JpaRepository<AlunoAtividade, Long>{

}
