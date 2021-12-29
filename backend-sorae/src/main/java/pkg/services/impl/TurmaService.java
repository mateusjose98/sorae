package pkg.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pkg.entities.projections.AtividadeResumoDTO;
import pkg.entities.projections.TurmaResumoDTO;
import pkg.repositories.TurmaRepository;

@Service
public class TurmaService {
	
	
	@Autowired
	private TurmaRepository repository;
	
	public List<TurmaResumoDTO> buscarDisciplinasDoAluno(Long idAluno) {
		return repository.buscarDisciplinasDoAluno(idAluno);
	}
	
	public List<AtividadeResumoDTO> buscarAtividadesDaTurmaDoAluno(Long idAluno) {
		return repository.buscarAtividadesDaTurmaDoAluno(idAluno);
	}
	

}
