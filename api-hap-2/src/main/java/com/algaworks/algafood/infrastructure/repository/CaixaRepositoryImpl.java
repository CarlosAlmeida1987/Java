package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.model.ContaApagar;
import com.algaworks.algafood.domain.repository.ContaRepository;

@Component
public class CaixaRepositoryImpl implements ContaRepository {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<ContaApagar> listar() {
		return manager.createQuery("from ContaApagar", ContaApagar.class)
				.getResultList();
	}
	
	@Override
	public ContaApagar buscar(Long id_cont) {
		return manager.find(ContaApagar.class, id_cont);
	}
	
	@Transactional
	@Override
	public ContaApagar salvar(ContaApagar contaapagar) {
		return manager.merge(contaapagar);
	}
	
	@Transactional
	@Override
	public void remover(Long id_cont) {
		ContaApagar contaapagar = buscar(id_cont);
		
		if (contaapagar == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		manager.remove(contaapagar);
	}


}
