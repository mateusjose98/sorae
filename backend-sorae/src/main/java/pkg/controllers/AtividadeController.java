package pkg.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import pkg.models.Atividade;
import pkg.services.AtividadeService;

@RestController
@RequestMapping(value = "/atividades")
public class AtividadeController {
	
	@Autowired
	private AtividadeService service;
	
	@PostMapping @ResponseStatus(HttpStatus.CREATED)
	public Atividade salvar(@RequestBody Atividade atividade) {
		return service.salvar(atividade);
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
