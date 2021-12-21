package pkg.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pkg.models.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {

}
