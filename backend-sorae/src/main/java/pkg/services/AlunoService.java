package pkg.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pkg.models.Aluno;
import pkg.repositories.AlunoRepository;

import java.util.Optional;

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
