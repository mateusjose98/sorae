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

import pkg.entities.Disciplina;
import pkg.services.impl.DisciplinaService;

@RestController
@RequestMapping("/disciplina")
public class DisciplinaController {

    @Autowired
    private DisciplinaService disciplinaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Disciplina salvarDisciplina (@RequestBody Disciplina disciplina){
        return disciplinaService.salvarDisciplina(disciplina);
    }

    @GetMapping("/{id}")
    public Disciplina acharDisciplina(@PathVariable Long id){
        return disciplinaService
                .acharDisciplina(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/deleta")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarDisciplina(@RequestBody Disciplina disciplina){
        disciplinaService.deletar(disciplina);
    }

    @PutMapping("/atualizar")
    @ResponseStatus(HttpStatus.OK)
    public Disciplina atualizarDisciplina(@RequestBody Disciplina disciplina){
        return disciplinaService.salvarDisciplina(disciplina);
    }

}

