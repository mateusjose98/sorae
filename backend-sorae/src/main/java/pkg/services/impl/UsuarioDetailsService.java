package pkg.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pkg.entities.PostgresUserDetails;
import pkg.entities.Usuario;
import pkg.enums.NivelEnum;
import pkg.exception.CustomException;
import pkg.repositories.UsuarioRepository;

@Service
public class UsuarioDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = repository.findByUsername(username).orElse(null);
		if (usuario == null || usuario.getNiveis() == null || usuario.getNiveis().isEmpty()) {
			throw new CustomException("Username ou password inválido.", HttpStatus.UNAUTHORIZED);
		}
		String[] authorities = new String[usuario.getNiveis().size()];
		int count = 0;
		for (NivelEnum nivel : usuario.getNiveis()) {
			// NOTE: normally we dont need to add "ROLE_" prefix. Spring does automatically
			// for us.
			// Since we are using custom token using JWT we should add ROLE_ prefix
			authorities[count] = "ROLE_" + nivel.name();
			count++;
		}
		PostgresUserDetails userDetails = new PostgresUserDetails(usuario.getUsername(), usuario.getPassword(),
				true, false, false, true,
				authorities);
		return userDetails;
	}
}
