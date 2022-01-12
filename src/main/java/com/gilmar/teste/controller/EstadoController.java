package com.gilmar.teste.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gilmar.teste.model.EstadoModel;
import com.gilmar.teste.repository.EstadoRepository;
import com.gilmar.teste.service.EstadoService;

/**
 * 
 * @author Junior
 *
 */

@RestController
@RequestMapping(value = "/estados")
public class EstadoController {

	@Autowired 
	private EstadoRepository estadoRepository;
	private EstadoService estadoService;
	/**
	 * Lista todos os estados
	 * @return
	 */
	@GetMapping
	public List<EstadoModel> listarEstados(){
		return estadoRepository.findAll();
	}
	
	/**
	 * Adiciona um novo estado
	 * @param estado
	 * @return
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EstadoModel adicionarEstado(@Valid @RequestBody EstadoModel estado) {
		return estadoRepository.save(estado);
	}
}
