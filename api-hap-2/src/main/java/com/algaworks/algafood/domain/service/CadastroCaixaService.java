package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.ContaApagar;
import com.algaworks.algafood.domain.repository.ContaRepository;

@Service
public class CadastroCaixaService {
	
	@Autowired
	private ContaRepository contarepository;
	
	public ContaApagar salvar(ContaApagar contaapagar) {
		return contarepository.salvar(contaapagar);
	}
	
	public void excluir(Long id_cont) {
		try {
			contarepository.remover(id_cont);
			
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
				String.format("Não existe um cadastro de cozinha com código %d", id_cont));
		
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
				String.format("Cozinha de código %d não pode ser removida, pois está em uso", id_cont));
		}
	}

}
