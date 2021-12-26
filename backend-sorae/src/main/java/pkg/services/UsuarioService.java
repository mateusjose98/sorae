package pkg.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import pkg.models.Usuario;
import pkg.repositories.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    
    public Optional<Usuario> buscarUsuarioCompleto(String login) {
    	return usuarioRepository.findByLogin(login);
    }


    public UserDetails autenticar(Usuario usuario) {
        UserDetails u = loadUserByUsername(usuario.getLogin());
         boolean senhasOk = passwordEncoder.matches(usuario.getSenha(), u.getPassword());
        if(senhasOk){
        	
            return u;
        }

        throw new RuntimeException("Senha inválida");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository
                .findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado!"));

        List<String> roles = usuario.getPerfis().stream()
        		.map(i -> i.getDescricao()).collect(Collectors.toList());
        return User.builder()
                .username(usuario.getLogin())
                .password(usuario.getSenha())
                .roles(roles.toArray(new String[0]))
                .build();




    // MOCK
        /*
        if (!username.equals("MATEUS")) {
            System.out.println(username);

            throw new UsernameNotFoundException("Usuário não encontrado!");
        }


        return User.builder()
                .username("MATEUS")
                .password(passwordEncoder.encode("123"))
                .roles("OPERADOR", "ADMIN")
                .build();
        */
    }

    @Transactional
    public Usuario salvar(Usuario usr) {
        return usuarioRepository.save(usr);
    }
}
