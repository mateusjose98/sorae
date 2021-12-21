package pkg.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import pkg.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	Optional<Usuario> findByLogin(String login);

}
