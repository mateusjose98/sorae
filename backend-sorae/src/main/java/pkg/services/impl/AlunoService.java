package pkg.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pkg.entities.Aluno;
import pkg.repositories.AlunoRepository;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public Aluno cadastraAluno(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    public Optional<Aluno> acharPorMatricula(Long matricula) {
        return alunoRepository.findById(matricula);
    }

    public void deletarAluno(Aluno aluno) {
        if(aluno != null) {
            alunoRepository.delete(aluno);
        }
    }
}
