package pkg.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pkg.entities.TurmaDisciplina;
import pkg.repositories.TurmaDisciplinaRepository;

@Service
public class TurmaDisciplinaService {

    @Autowired
    private TurmaDisciplinaRepository turmaDisciplinaRepository;


    public TurmaDisciplina buscarDisciplinasDoProfessorPorId(Long idTurmaDisciplina) {
        return turmaDisciplinaRepository.findById(idTurmaDisciplina).get();
    }

}
