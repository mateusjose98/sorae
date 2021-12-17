package pkg.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pkg.models.Atividade;

public interface AtividadeRepository extends JpaRepository<Atividade, Long> {

}
