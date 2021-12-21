package pkg.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pkg.models.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
