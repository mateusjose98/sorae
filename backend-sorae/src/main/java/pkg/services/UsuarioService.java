package pkg.services;

import java.util.List;

import org.springframework.data.domain.Page;

import pkg.dtos.UsuarioDTO;
import pkg.entities.Usuario;

public interface UsuarioService {

	Usuario create(UsuarioDTO usuario);
    
    Usuario update(Usuario usuario);
    
    Usuario updatePassword(Usuario usuario);
    
    Usuario findById(Long id);
    
    Usuario findByUsername(String username);
    
    Page<Usuario> findByNome(int page, int count, String nome);
    
    Page<Usuario> findAll(int page, int size);
    
    List<UsuarioDTO> findAll();
    
    void delete(Long id);
}
