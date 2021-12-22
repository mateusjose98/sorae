package pkg.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pkg.models.Aluno;
import pkg.services.AlunoService;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;


    @PostMapping("/cadastro") @ResponseStatus(HttpStatus.CREATED)
    public Aluno cadastraAluno(@RequestBody Aluno aluno) {
        return alunoService.cadastraAluno(aluno);
    }


    @GetMapping("/{matricula}")
    public Aluno acharPorMatricula(@PathVariable Long matricula){
        return alunoService
                .acharPorMatricula(matricula)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND) );
    }


    @PutMapping("/atualizar") @ResponseStatus(HttpStatus.OK)
    public Aluno atualizarCadastroAluno(@RequestBody Aluno aluno){
        return alunoService.cadastraAluno(aluno);
    }


    @DeleteMapping("/{matricula}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarAluno(@PathVariable Long matricula){
        alunoService
                .acharPorMatricula(matricula)
                .map(aluno -> {
                    alunoService.deletarAluno(aluno);
                    return Void.TYPE;
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}
