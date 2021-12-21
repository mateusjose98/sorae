package pkg.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import pkg.models.Professor;
import pkg.services.ProfessorService;

@RestController
@RequestMapping("/ professor")

public class ProfessorController {

    @Autowired
    private ProfessorService professorService;


    @PostMapping(" /cadastro") @ResponseStatus(HttpStatus.CREATED)
    public Professor cadastraProfessor(@RequestBody Professor professor) {
        return professorService.cadastraProfessor(professor);
    }

    @GetMapping(" /idprofessor")
    public Professor acharPorIdProfessor(@PathVariable Long idprofessor){
        return professorService
                .acharPorIdprofessor(idprofessor)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND) );

    }

    @PutMapping(" /atualizar") @ResponseStatus(HttpStatus.OK)
    public Professor atualizarCadastroProfessor(@RequestBody Professor professor){
        return professorService.cadastraProfessor(professor);
    }

    @DeleteMapping("/idprofessor")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarProfessor(@PathVarible Long idprofessor) {
        professorService        
                .acharPorIdProfessor(idprofessor)
                .map(professor -> {
                    professorservice.deletarProfessor(professor);
                    return Void.TYPE;
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND) );

    })


    
}
