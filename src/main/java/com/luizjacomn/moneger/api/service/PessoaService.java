package com.luizjacomn.moneger.api.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.luizjacomn.moneger.api.event.RecursoCriadoEvent;
import com.luizjacomn.moneger.api.model.Pessoa;
import com.luizjacomn.moneger.api.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository repository;

	@Autowired
	private ApplicationEventPublisher publisher;

	public List<Pessoa> buscarTodas() {
		return repository.findAll();
	}

	public Optional<Pessoa> buscarPorId(Long id) {
		Optional<Pessoa> optional = repository.findById(id);
		if (!optional.isPresent())
			throw new EmptyResultDataAccessException(1);
		else
			return optional;
	}

	public Pessoa salvar(Pessoa pessoa, HttpServletResponse response) {
		Pessoa pessoaSalva = repository.save(pessoa);

		publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaSalva.getId()));
		return pessoaSalva;
	}

	public Pessoa atualizar(Long id, Pessoa pessoa) {
		Pessoa pessoaSalva = buscarPorId(id).get();
		BeanUtils.copyProperties(pessoa, pessoaSalva, "id");
		return repository.save(pessoaSalva);
	}
	
	public void atualizar(Long id, Boolean ativo) {
		Pessoa pessoaSalva = buscarPorId(id).get();
		pessoaSalva.setAtivo(ativo);
		repository.save(pessoaSalva);
	}

	public void remover(Long id) {
		repository.deleteById(id);
	}
}