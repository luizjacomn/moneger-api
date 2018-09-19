package com.luizjacomn.moneger.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.luizjacomn.moneger.api.model.Lancamento;
import com.luizjacomn.moneger.api.repository.LancamentoRepository;

@Service
public class LancamentoService {

	@Autowired
	private LancamentoRepository repository;
	
	public List<Lancamento> buscarTodos() {
		return repository.findAll();
	}
	
	public Optional<Lancamento> buscarPorId(Long id) {
		Optional<Lancamento> optional = repository.findById(id);
		if (!optional.isPresent())
			throw new EmptyResultDataAccessException(1);
		else
			return optional;
	}
}