package com.algaworks.algafood.domain.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class ContaReceber {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_cont;
	
	@Column(nullable = false)
	private String ds_nome;
	
	@Column(nullable = true)
	private Date dt_data;
	
	@Column(nullable = false)
	private BigDecimal valor;

}
