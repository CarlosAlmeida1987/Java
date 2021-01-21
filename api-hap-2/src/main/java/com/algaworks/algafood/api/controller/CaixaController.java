package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.ContaApagar;
import com.algaworks.algafood.domain.repository.ContaRepository;
import com.algaworks.algafood.domain.service.CadastroCaixaService;

@RestController
@RequestMapping(value = "/caixa")
public class CaixaController {
	
	@Autowired
	private ContaRepository contarepository;
	
	@Autowired
	private CadastroCaixaService cadastrocaixa;
	
	@GetMapping(produces = { MediaType.APPLICATION_PROBLEM_JSON_VALUE})
	public List<ContaApagar> listar() {
		return contarepository.listar();
	}
	
	@GetMapping("/{id_cont}")
	public ResponseEntity<ContaApagar> buscar(@PathVariable Long id_cont) {
		ContaApagar contaapagar = contarepository.buscar(id_cont);
		
		if (contaapagar != null) {
			return ResponseEntity.ok(contaapagar);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ContaApagar adicionar(@RequestBody ContaApagar contaapagar) {
		return cadastrocaixa.salvar(contaapagar);
	}
	
	@PutMapping("/{id_cont}")
	public ResponseEntity<ContaApagar> atualizar(@PathVariable Long id_cont,
			@RequestBody ContaApagar contaapagar) {
		ContaApagar caixaAtual = contarepository.buscar(id_cont);
		
		if (contaapagar != null) {
			BeanUtils.copyProperties(contaapagar, caixaAtual, "id_cont");
			
			caixaAtual = cadastrocaixa.salvar(caixaAtual);
			return ResponseEntity.ok(caixaAtual);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id_cont}")
	public ResponseEntity<?> remover(@PathVariable Long id_cont) {
		try {
			cadastrocaixa.excluir(id_cont);	
			return ResponseEntity.noContent().build();
			
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
			
		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body(e.getMessage());
		}
	}

}
