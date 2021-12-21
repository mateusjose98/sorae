package pkg.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pkg.models.Usuario;
import pkg.repositories.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService{
	
	@Autowired
	private UsuarioRepository repository;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Usuario usuarioDoBanco = repository
									.findByLogin(login)
									.orElseThrow( () -> new UsernameNotFoundException("Usuário com este login foi não encontrado!"));
		return usuarioDoBanco;
	}

}
