package pkg.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pkg.entities.Disciplina;
import pkg.repositories.DisciplinaRepository;
import pkg.repositories.TurmaDisciplinaRepository;


@Service
public class DisciplinaService {

    @Autowired
    private DisciplinaRepository disciplinaRepository;
	
	@Autowired
	private TurmaDisciplinaRepository turmaDisciplinaRepository;

    public Disciplina salvarDisciplina(Disciplina disciplina) {
        return disciplinaRepository.save(disciplina);
    }

    public Optional<Disciplina> acharDisciplina(Long id) {
        return disciplinaRepository.findById(id);
    }

    public void deletar(Disciplina disciplina) {
    	
        if (disciplina != null){
            disciplinaRepository.delete(disciplina);
        }
    }
}
