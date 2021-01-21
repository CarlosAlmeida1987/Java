package com.algaworks.algafood.domain.repository;

import java.util.List;

import com.algaworks.algafood.domain.model.ContaApagar;

public interface ContaRepository {
	
	List<ContaApagar> listar();
	ContaApagar buscar(Long id_cont);
	ContaApagar salvar(ContaApagar contaapagar);
	void remover(Long id_cont);

}
