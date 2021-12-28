package pkg.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pkg.entities.Atividade;

public interface AtividadeRepository extends JpaRepository<Atividade, Long> {

}
