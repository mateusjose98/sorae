package pkg.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pkg.entities.TurmaDisciplina;

@Repository
public interface TurmaDisciplinaRepository extends JpaRepository<TurmaDisciplina, Long> {
}
