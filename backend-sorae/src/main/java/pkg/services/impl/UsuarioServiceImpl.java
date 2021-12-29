package pkg.services.impl;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pkg.dtos.UsuarioDTO;
import pkg.entities.Aluno;
import pkg.entities.Professor;
import pkg.entities.Usuario;
import pkg.enums.NivelEnum;
import pkg.exception.CustomException;
import pkg.repositories.AlunoRepository;
import pkg.repositories.ProfessorRepository;
import pkg.repositories.UsuarioRepository;
import pkg.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AlunoRepository alunoRepository;
	
	@Autowired
	private ProfessorRepository professorRepository;

	@Override
	@Transactional
	public Usuario create(UsuarioDTO usuario) {
		if (usuario.getId() != null && usuarioRepository.existsById(usuario.getId()))
			throw new CustomException("ID de Usuário (" + usuario.getId() + ") já utilizado.", HttpStatus.BAD_REQUEST);
		
		if(usuario.getNiveis() != null && usuario.getNiveis().get(0).equals(NivelEnum.ALUNO)) {
			Aluno aluno = new Aluno();
			usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
			BeanUtils.copyProperties(usuario, aluno);
			return alunoRepository.save(aluno);

		}
		if(usuario.getNiveis() != null && usuario.getNiveis().get(0).equals(NivelEnum.PROFESSOR)) {
			Professor professor = new Professor();
			usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
			BeanUtils.copyProperties(usuario, professor);
			return professorRepository.save(professor);
		}
		
		throw new CustomException("Nenhum usuário compatível foi informado!", HttpStatus.BAD_REQUEST);
		
		
	}

	@Override
	@Transactional
	public Usuario update(Usuario usuario) {
		
		Object usuarioLogadoInContext = SecurityContextHolder.getContext()
    			.getAuthentication()
    			.getPrincipal();
		
    	Collection<? extends GrantedAuthority> authoritiesUsuarioLogado = (((UserDetails) usuarioLogadoInContext).getAuthorities());
    	List<String> stringAuths = authoritiesUsuarioLogado.stream().map(x -> x.toString()).collect(Collectors.toList());
    	
    	String userName = (((UserDetails) usuarioLogadoInContext).getUsername());
    	Usuario usuarioLogado = usuarioRepository.findByUsername(userName).get();
    	
    	System.out.printf("usuario logado : %s suas atuh : %s", usuarioLogado, stringAuths);
    	
    	if (!stringAuths.contains("ROLE_ADMIN") && usuario.getId() != usuarioLogado.getId()) {
    		System.out.println("não sou permitido nem como admin nem com id correto");
    		throw new CustomException("Você não possui essa permissão!", HttpStatus.BAD_REQUEST);
    	}
    	

		if (usuario.getId() == null)
			throw new CustomException("ID da Usuário não informado.", HttpStatus.BAD_REQUEST);
		if (!usuarioRepository.existsById(usuario.getId()))
			throw new CustomException("Usuario não encontrado.", HttpStatus.BAD_REQUEST);
		if (usuario.getPassword() == null) {
			usuario.setPassword(usuarioRepository.findById(usuario.getId()).get().getPassword());
		}else {
			usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		}
			
		return usuarioRepository.save(usuario);
	}

	@Override
	@Transactional
	public Usuario updatePassword(Usuario usuario) {
		if (usuario.getId() == null)
			throw new CustomException("ID da Usuário não informado.", HttpStatus.BAD_REQUEST);
		if (!usuarioRepository.existsById(usuario.getId()))
			throw new CustomException("Usuario não encontrado.", HttpStatus.BAD_REQUEST);
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		return usuarioRepository.save(usuario);
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findByUsername(String username) {
		Optional<Usuario> opt = usuarioRepository.findByUsername(username);
		if (opt.isPresent())
			return opt.get();
		else
			throw new CustomException("Usuario não encontrado", HttpStatus.ACCEPTED);
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findById(Long id) {
		Optional<Usuario> opt = usuarioRepository.findById(id);
		if (opt.isPresent())
			return opt.get();
		else
			throw new CustomException("Usuario não encontrado", HttpStatus.ACCEPTED);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Usuario> findByNome(int page, int size, String nome) {
		Pageable pageable = PageRequest.of(page, size);
		return usuarioRepository.findByNomeContainingIgnoreCaseOrderByNomeAsc(nome, pageable);
	}

	@Override
	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}

	@Override
	public Page<Usuario> findAll(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return usuarioRepository.findAll(pageable);
	}

	@Override
	public void delete(Long id) {
		usuarioRepository.deleteById(id);
	}
}
