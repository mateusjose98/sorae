package pkg.controllers;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import pkg.dtos.AlunoRespostaAtividade;
import pkg.entities.Aluno;
import pkg.entities.AlunoAtividade;
import pkg.entities.Atividade;
import pkg.entities.projections.AtividadeDetalhe;
import pkg.repositories.AlunoAtividadeRepository;
import pkg.repositories.AlunoRepository;
import pkg.repositories.AtividadeRepository;
import pkg.services.impl.AtividadeService;

@RestController
@RequestMapping(value = "/atividades")
@CrossOrigin(origins = "*")
public class AtividadeController {
	
	@Autowired
	private AtividadeService service;
	
	@Autowired
	private AtividadeRepository repository;
	
	@Autowired
	private AlunoRepository repositoryAluno;
	
	@Autowired
	private AlunoAtividadeRepository repositoryAlunoAtividade;
	

	@PostMapping @ResponseStatus(HttpStatus.CREATED)
	public Atividade salvar(@RequestBody Atividade atividade) {
		return service.salvar(atividade);
	}
	
	@PostMapping("/responder") @Transactional
	public ResponseEntity<String> responderAtividade(@RequestBody AlunoRespostaAtividade respostaDto){
		Long idAluno = respostaDto.getIdAluno();
		Aluno aluno = repositoryAluno.findById(idAluno).get();
		Atividade atividade = service.acharPorId(respostaDto.getIdAtividade()).get();
		AlunoAtividade alunoAtividade = new AlunoAtividade();
		alunoAtividade.setAluno(aluno);
		alunoAtividade.setAtividade(atividade);
		alunoAtividade.setResposta(respostaDto.getResposta());
		repositoryAlunoAtividade.save(alunoAtividade);
		return ResponseEntity.ok(String.format("Atividade %s da disciplina %s respondida com sucesso!", atividade.getTitulo(), atividade.getTurmasDisciplina().getDisciplina().getNome()));
	}
	
	@GetMapping("/detalhe/{idAtividade}")
	public AtividadeDetalhe buscarAtividadeDetalhadaPorId(@PathVariable("idAtividade") Long idAtividade) {
		return repository.buscarAtividadeDetalhadaPorId(idAtividade);
	}
	
	@GetMapping("/{id}")
	public Atividade acharPorId(@PathVariable Long id) {
		return service
				.acharPorId(id)
				.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND) );
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long id) {
		service
				.acharPorId(id)
				.map(atividade -> {
					service.deletar(atividade);
					return Void.TYPE;
				})
				.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND) );
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Atividade> atualizar( @PathVariable Long id, @RequestBody Atividade atividadeAtualizada) {
		
		Atividade atv = service
								.acharPorId(id).orElse(null);
		if(atv != null) {
			// atualiza todas as propriedades, exceto codigo
			BeanUtils.copyProperties(atividadeAtualizada, atv, "codigo");
			return ResponseEntity.ok(service.salvar(atividadeAtualizada));
			
		}
		
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}

}
