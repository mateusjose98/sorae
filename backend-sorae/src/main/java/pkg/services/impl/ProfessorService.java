package pkg.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pkg.entities.AlunoAtividade;
import pkg.entities.Atividade;
import pkg.entities.Professor;
import pkg.repositories.ProfessorRepository;

@Service

public class ProfessorService {
    
    @Autowired
    private ProfessorRepository professorRepository;
    @Autowired
    private TurmaDisciplinaService turmaDisciplinaService;

    public Professor cadastraProfessor(Professor professor) {
        return professorRepository.save(professor);

    }

    public Optional<Professor> acharPorIdprofessor(long idprofessor){
        return professorRepository.findById(idprofessor);
    }

    public void deletarProfessor(Professor professor) {
        if(professor != null) {
            professorRepository.delete(professor);
            
        }
    }


    public List<String> buscarDisciplinaDeProfessor(Long idprofessor) {
        List<Long> idTurmaDisciplina = new ArrayList<>();

        List<String> listaDisciplinas = new ArrayList<>();


        professorRepository.findById(idprofessor).get().getTurmas()
                .forEach(turmaDisciplina -> {
                    idTurmaDisciplina.add(turmaDisciplina.getId());
                });

        idTurmaDisciplina.forEach(id -> {
            listaDisciplinas.add(turmaDisciplinaService.buscarDisciplinasDoProfessorPorId(id).getDisciplina().getNome());

        });

        return listaDisciplinas;
    }


    public List buscarAtividadesDeProfessor(Long idprofessor) {

        List<Long> idTurma = new ArrayList<>();

        List<Atividade> listaAtividades = new ArrayList<>();

        professorRepository.findById(idprofessor).get().getTurmas()
                .forEach(turmaDisciplina -> {
                    idTurma.add(turmaDisciplina.getId());
                });

        idTurma.forEach(idTD ->{
            List<Atividade> listaAtividadesTemporaria = turmaDisciplinaService.buscarAtividadeDeTD(idTD);

            listaAtividadesTemporaria.forEach(atv ->{
                listaAtividades.add(atv);
            });
        });

        return listaAtividades;
    }


    public List buscarRespostaAtividadesDeProfessor(List<Atividade> listAtv) {

        List<Atividade> listaAtividades = listAtv;
        List<AlunoAtividade> listAlunoAtv = new ArrayList<>();

        listaAtividades.forEach(atv ->{
            List<AlunoAtividade> listAlunoAtvTemporario = atv.getAlunoAtividade();
            listAlunoAtvTemporario.forEach(alunoAtvTemp ->{
                if (alunoAtvTemp != null){
                    listAlunoAtv.add(alunoAtvTemp);
                }
            });
        });
        return listAlunoAtv;
    }
}
