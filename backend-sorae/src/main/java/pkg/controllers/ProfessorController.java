package pkg.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import pkg.entities.Professor;
import pkg.services.impl.ProfessorService;

@RestController
@RequestMapping("/professor")

public class ProfessorController {

    @Autowired
    private ProfessorService professorService;


    @PostMapping("/cadastro") @ResponseStatus(HttpStatus.CREATED)
    public Professor cadastraProfessor(@RequestBody Professor professor) {
        return professorService.cadastraProfessor(professor);
    }

    @GetMapping("/idprofessor")
    public Professor acharPorIdProfessor(@PathVariable Long idprofessor){
        return professorService
                .acharPorIdprofessor(idprofessor)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND) );

    }

    @PutMapping("/atualizar") @ResponseStatus(HttpStatus.OK)
    public Professor atualizarCadastroProfessor(@RequestBody Professor professor){
        return professorService.cadastraProfessor(professor);
    }

    @DeleteMapping("/idprofessor")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarProfessor(@PathVariable Long idprofessor) {
        professorService        
                .acharPorIdprofessor(idprofessor)
                .map(professor -> {
                	professorService.deletarProfessor(professor);
                    return Void.TYPE;
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND) );

    }


    
}
