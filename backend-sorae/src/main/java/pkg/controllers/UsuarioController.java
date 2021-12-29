package pkg.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pkg.entities.Usuario;
import pkg.response.Response;
import pkg.services.UsuarioService;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping
	public ResponseEntity<Response<Usuario>> create(@RequestBody Usuario usuario, BindingResult result,
			@RequestHeader(name = "Authorization", required = false) String authorization) {
		Response<Usuario> response = new Response<>();
		response.setData(usuarioService.create(usuario));
		return ResponseEntity.ok(response);
	}

	@PutMapping
	public ResponseEntity<Response<Usuario>> update(@RequestBody Usuario usuario, BindingResult result,
			@RequestHeader(name = "Authorization", required = false) String authorization) {
		Response<Usuario> response = new Response<>();
		response.setData(usuarioService.update(usuario));
		return ResponseEntity.ok(response);
	}


	@PutMapping(value = "/alterar-senha")
	public ResponseEntity<Response<Usuario>> updatePassword(
			@RequestHeader(name = "Authorization", required = false) String token,
			@RequestBody Usuario usuario) {
		Response<Usuario> response = new Response<>();
		Usuario temp = usuarioService.findById(usuario.getId());
		if (passwordEncoder.matches(usuario.getPassword(), temp.getPassword())) {
			temp.setPassword(usuario.getNewPassword());
			Usuario usr = usuarioService.updatePassword(temp);

			response.setData(usuarioService.findById(usr.getId()));
			return ResponseEntity.ok(response);
		} else {
			response.getErrors().add("Senha Incorreta");
			return ResponseEntity.badRequest().body(response);
		}
	}

	@PutMapping("password")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Usuario>> updatePassword(@RequestBody Usuario usuario, BindingResult result,
			@RequestHeader(name = "Authorization", required = false) String authorization) {
		Response<Usuario> response = new Response<>();
		response.setData(usuarioService.updatePassword(usuario));
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "{id}")
	public ResponseEntity<Response<Usuario>> findById(@PathVariable("id") Long id,
			@RequestHeader(name = "Authorization", required = false) String authorization) {
		Response<Usuario> response = new Response<>();
		response.setData(usuarioService.findById(id));
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "{page}/{size}")
	public ResponseEntity<Response<Page<Usuario>>> findAll(@PathVariable int page, @PathVariable int size,
			@RequestHeader(name = "Authorization", required = false) String authorization) {
		Response<Page<Usuario>> response = new Response<>();
		response.setData(usuarioService.findAll(page, size));
		return ResponseEntity.ok(response);
	}

	@GetMapping
	public ResponseEntity<Response<List<Usuario>>> findAll(
			@RequestHeader(name = "Authorization", required = false) String authorization) {
		Response<List<Usuario>> response = new Response<>();
		response.setData(usuarioService.findAll());
		return ResponseEntity.ok(response);
	}

	@DeleteMapping(value = "{id}")
	public ResponseEntity<Response<String>> delete(@PathVariable Long id,
			@RequestHeader(name = "Authorization", required = false) String token) {
		Response<String> response = new Response<>();
		try {
			usuarioService.delete(id);
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}
}
