package com.luizjacomn.moneger.api.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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

import com.luizjacomn.moneger.api.model.Pessoa;
import com.luizjacomn.moneger.api.service.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {

	@Autowired
	private PessoaService service;
	
	@GetMapping
	public List<Pessoa> buscarTodas() {
		return service.buscarTodas();
	}

	@PostMapping
	public ResponseEntity<Pessoa> salvar(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(pessoa, response));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Pessoa> buscarPorId(@PathVariable Long id) {
		Optional<Pessoa> optional = service.buscarPorId(id);
		return optional .isPresent() ? ResponseEntity.ok(optional.get()) : ResponseEntity.notFound().build(); 
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		service.remover(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Pessoa> atualizar(@PathVariable Long id, @Valid @RequestBody Pessoa pessoa) {
		return ResponseEntity.ok(service.atualizar(id, pessoa));
	}
	
	@PutMapping("/{id}/ativo")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void atualizar(@PathVariable Long id, @RequestBody Boolean ativo) {
		service.atualizar(id, ativo);
	}
}