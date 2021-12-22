package pkg.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import pkg.config.JwtService;
import pkg.models.Credenciais;
import pkg.models.Perfil;
import pkg.models.Token;
import pkg.models.Usuario;
import pkg.repositories.PerfilRepository;
import pkg.services.UsuarioService;

@RestController
@RequestMapping("/api")
public class LoginController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private PerfilRepository roleRepository;

	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping(value = "/cadastrar-usuario")
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario salvar(@RequestBody Usuario usuario) {
		// TODO validar a nulidade da senha
		List<Perfil> r = roleRepository.findAllById(usuario.getPerfis().stream()
				.map(p -> p.getId())
				.collect(Collectors.toList()));
		
		usuario.setPerfis(r);
		usuario.setSenha(passwordEncoder.encode(usuario.getSenha())); // CRIPTOGRAFAR SENHA
		return usuarioService.salvar(usuario);

	}

	@PostMapping("/auth/login")
	public Token autenticar(@RequestBody Credenciais cd) {

		try {
			Usuario usuario = new Usuario();
			usuario.setLogin(cd.getLogin());
			usuario.setSenha(cd.getSenha());
			usuarioService.autenticar(usuario);
			String token = jwtService.gerarToken(usuario);
			return new Token(usuario.getLogin(), token);

		} catch (RuntimeException e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
		}

	}

}
