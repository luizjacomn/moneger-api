package com.luizjacomn.moneger.api.service;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.luizjacomn.moneger.api.event.RecursoCriadoEvent;
import com.luizjacomn.moneger.api.exception.PessoaInexistenteOuInativaException;
import com.luizjacomn.moneger.api.model.Lancamento;
import com.luizjacomn.moneger.api.model.Pessoa;
import com.luizjacomn.moneger.api.repository.LancamentoRepository;
import com.luizjacomn.moneger.api.repository.PessoaRepository;
import com.luizjacomn.moneger.api.repository.filter.LancamentoFilter;

@Service
public class LancamentoService {

	@Autowired
	private LancamentoRepository repository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	public Page<Lancamento> filtrar(LancamentoFilter filter, Pageable pageable) {
		return repository.filtrar(filter, pageable);
	}
	
	public Optional<Lancamento> buscarPorId(Long id) {
		Optional<Lancamento> optional = repository.findById(id);
		if (!optional.isPresent())
			throw new EmptyResultDataAccessException(1);
		else
			return optional;
	}
	
	public Lancamento salvar(Lancamento lancamento, HttpServletResponse response) {
		Optional<Pessoa> pessoa = pessoaRepository.findById(lancamento.getPessoa().getId());
		
		if(!pessoa.isPresent() || pessoa.get().isInativo())
			throw new PessoaInexistenteOuInativaException();
		
		Lancamento lancamentoSalvo = repository.save(lancamento);

		publisher.publishEvent(new RecursoCriadoEvent(this, response, lancamentoSalvo.getId()));
		return lancamentoSalvo;
	}

	public Lancamento atualizar(Long id, Lancamento lancamento) {
		Lancamento lancamentoSalva = buscarPorId(id).get();
		BeanUtils.copyProperties(lancamento, lancamentoSalva, "id");
		return repository.save(lancamentoSalva);
	}
	
	public void remover(Long id) {
		repository.deleteById(id);
	}
}