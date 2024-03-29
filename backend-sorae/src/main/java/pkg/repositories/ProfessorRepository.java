package pkg.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pkg.entities.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {

}
