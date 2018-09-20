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
import com.luizjacomn.moneger.api.model.Categoria;
import com.luizjacomn.moneger.api.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	public List<Categoria> buscarTodas() {
		return repository.findAll();
	}

	public Optional<Categoria> buscarPorId(Long id) {
		Optional<Categoria> optional = repository.findById(id);
		if (!optional.isPresent())
			throw new EmptyResultDataAccessException(1);
		else
			return optional;
	}

	public Categoria salvar(Categoria categoria, HttpServletResponse response) {
		Categoria categoriaSalva = repository.save(categoria);

		publisher.publishEvent(new RecursoCriadoEvent(this, response, categoriaSalva.getId()));
		return categoriaSalva;
	}

	public Categoria atualizar(Long id, Categoria categoria) {
		Categoria categoriaSalva = buscarPorId(id).get();
		BeanUtils.copyProperties(categoria, categoriaSalva, "id");
		return repository.save(categoriaSalva);
	}

	public void remover(Long id) {
		repository.deleteById(id);
	}
}