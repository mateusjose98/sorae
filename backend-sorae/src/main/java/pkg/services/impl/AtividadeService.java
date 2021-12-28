package pkg.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pkg.entities.Atividade;
import pkg.repositories.AtividadeRepository;

@Service
public class AtividadeService {
	
	@Autowired
	private AtividadeRepository repository;

	public Atividade salvar(Atividade atividade) {
		
		return repository.save(atividade);
	}

	public Optional<Atividade> acharPorId(Long id) {
		
		return repository.findById(id);
	}

	public void deletar(Atividade atividade) {
		
		if(atividade != null) {
			repository.delete(atividade);
		}
		
	}
	
	
	
	

}
