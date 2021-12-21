package pkg.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pkg.models.Professor;
import pkg.repositories.ProfessorRepository;

@Service

public class ProfessorService {
    
    @Autowired
    private ProfessorRepository professorRepository;

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

   
    
}
