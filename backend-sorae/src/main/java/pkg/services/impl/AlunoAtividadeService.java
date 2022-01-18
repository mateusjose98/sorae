package pkg.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pkg.entities.AlunoAtividade;
import pkg.entities.Atividade;
import pkg.repositories.AlunoAtividadeRepository;
import pkg.repositories.AtividadeRepository;

import java.util.ArrayList;
import java.util.List;


@Service
public class AlunoAtividadeService {

    @Autowired
    AlunoAtividadeRepository alunoAtividadeRepository;
    @Autowired
    AtividadeRepository atividadeRepository;



    public List<AlunoAtividade> buscarRespostasAtividadesDeProfessor(Long idatividade) {

        List<Long> idAlunoAtv = new ArrayList<>();
        List<AlunoAtividade> listaAlunoAtv = new ArrayList<>();


        atividadeRepository.findById(idatividade).get().getAlunoAtividade()
                .forEach(alunoAtividade -> {
                    idAlunoAtv.add(alunoAtividade.getId());
                });


        idAlunoAtv.forEach(idAAtv ->{
            listaAlunoAtv.add(alunoAtividadeRepository.findById(idAAtv).get());
        });


        return listaAlunoAtv;
    }
}
