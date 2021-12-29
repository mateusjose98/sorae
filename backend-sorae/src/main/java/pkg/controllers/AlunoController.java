package pkg.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import pkg.entities.Aluno;
import pkg.entities.projections.AtividadeResumoDTO;
import pkg.entities.projections.TurmaResumoDTO;
import pkg.services.impl.AlunoService;
import pkg.services.impl.TurmaService;

@RestController
@RequestMapping("/alunos")
@CrossOrigin(origins = "*")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;
    
    @Autowired
    private TurmaService turmaService;
    
    
    
    @GetMapping("/{idAluno}/atividades")
    public List<AtividadeResumoDTO> buscarAtividadesDaTurmaDoAluno(@PathVariable("idAluno") Long idAluno){
    	return turmaService.buscarAtividadesDaTurmaDoAluno(idAluno);
    }
    
    
    @GetMapping("/{idAluno}/disciplinas")
    public List<TurmaResumoDTO> buscarDisciplinasDoAluno(@PathVariable("idAluno") Long idAluno){
    	return turmaService.buscarDisciplinasDoAluno(idAluno);
    }


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
