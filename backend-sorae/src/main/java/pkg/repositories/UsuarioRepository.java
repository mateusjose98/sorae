package pkg.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pkg.entities.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Optional<Usuario> findByUsername(String cpfCnpj);
    
    Page<Usuario> findByNomeContainingIgnoreCaseOrderByNomeAsc(String nome, Pageable pageable);
}
