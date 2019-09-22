package com.algaworks.comercial.controller;

import com.algaworks.comercial.model.Oportunidade;
import com.algaworks.comercial.repository.OportunidadeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/oportunidades")
public class OportunidadeController {

	private final OportunidadeRepository repository;

	public OportunidadeController(OportunidadeRepository repository) {
		this.repository = repository;
	}

	@GetMapping
	public List<Oportunidade> listar() {
		return repository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Oportunidade> buscar(@PathVariable Long id) {
		Optional<Oportunidade> oportunidade = repository.findById(id);
		
		if (oportunidade.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(oportunidade.get());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Oportunidade adicionar(@Valid @RequestBody Oportunidade oportunidade) {
		Optional<Oportunidade> oportunidadeExistente = repository
				.findByDescricaoAndNomeProspecto(oportunidade.getDescricao(), 
						oportunidade.getNomeProspecto());
		
		if (oportunidadeExistente.isPresent()) {
			throw new ResponseStatusException(HttpStatus.CONFLICT,
					"Já existe uma oportunidade para este prospecto com a mesma descrição");
		}
		
		return repository.save(oportunidade);
	}
}
