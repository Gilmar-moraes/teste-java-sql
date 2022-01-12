package com.gilmar.teste.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cidades")
public class CidadeModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(name = "id_cidades")
	private Long id;
	
	@NotBlank(message = "O campo populacao não pode esta em branco")
	@Size(max = 45)
	private String nome;
	
	@NotBlank(message = "O campo populacao não pode esta em branco")
	private double populacao;
	
	@NotBlank(message = "O campo área não pode esta em branco")
	private double area;
	
	@NotBlank(message = "O campo idEstado não pode esta em branco")
	@ManyToOne
	@JoinColumn(name = "codigo_estado")
	private EstadoModel idEstado;
}