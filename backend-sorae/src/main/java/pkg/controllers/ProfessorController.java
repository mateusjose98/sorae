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

import pkg.entities.Atividade;
import pkg.entities.Professor;
import pkg.services.impl.AlunoAtividadeService;
import pkg.services.impl.AtividadeService;
import pkg.services.impl.ProfessorService;
import pkg.services.impl.TurmaDisciplinaService;

@RestController
@RequestMapping("/professor")
@CrossOrigin(origins = "*")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;
    @Autowired
    private TurmaDisciplinaService turmaDisciplinaService;
    @Autowired
    private AlunoAtividadeService alunoAtividadeService;
    @Autowired
    private AtividadeService atividadeService;


    @PostMapping("/cadastro") @ResponseStatus(HttpStatus.CREATED)
    public Professor cadastraProfessor(@RequestBody Professor professor) {
        return professorService.cadastraProfessor(professor);
    }

    @GetMapping("/{idprofessor}")
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

    @GetMapping("/{idprofessor}/disciplinas")
    public List acharDisciplinasDeProfessor(@PathVariable ("idprofessor") Long idprofessor){

        return professorService.buscarDisciplinaDeProfessor(idprofessor);
    }

    @GetMapping("/{idprofessor}/atividades")
    public List acharAtividadesDeProfessor(@PathVariable ("idprofessor") Long idprofessor){

        return professorService.buscarAtividadesDeProfessor(idprofessor);
    }

    @GetMapping("/{idprofessor}/atividades/respostas")
    public List repostasDasAtividadesDeProfessor(@PathVariable ("idprofessor") Long idprofessor){

        List<Atividade> listAtv = acharAtividadesDeProfessor(idprofessor);

        return professorService.buscarRespostaAtividadesDeProfessor(listAtv);
    }


//    @GetMapping("/{idatividade}/resposta")
//    public List<AlunoAtividade> respostaDeAtividade(@PathVariable ("idatividade") Long idatividade){
//
//        return alunoAtividadeService.buscarRespostasAtividadesDeProfessor(idatividade);
//
//    }


}
