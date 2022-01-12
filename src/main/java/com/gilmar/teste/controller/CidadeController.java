package com.gilmar.teste.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gilmar.teste.model.CidadeModel;
import com.gilmar.teste.model.EstadoModel;
import com.gilmar.teste.repository.CidadeRepository;
import com.gilmar.teste.service.CidadeService;

/**
 * 
 * @author Junior
 *
 */

@RestController
@RequestMapping(value = "/cidade")
public class CidadeController {

	@Autowired
	private CidadeRepository cidadeRepository;
	private CidadeService cidadeService;
	
	/**
	 * Lista todas as cidades
	 * @return
	 */
	@GetMapping
	public List<CidadeModel> listar(){
		return cidadeRepository.findAll();
	}
	
	/**
	 * Quantidade de cidades por UF
	 */
	@GetMapping("/{estadoId}")
	public List<CidadeModel> listarTotalDeCidadesNaUF(EstadoModel estadoId){
		return cidadeRepository.findByCidadeUf(estadoId.getId());
		
	}
	
	/**
	 * Quantidade de áreas por UF
	 *
	@GetMapping("/{idEstadoId}")
	public List<CidadeModel> listarQuantAreaUF(EstadoModel idEstado){
		return cidadeRepository.findByAreaPorUf(idEstado.getId());
	}*/
	
	/** 
	 * Quantidade de cidades com uma
	 * população maior de 10.000
	 *
	@GetMapping("/{populacao}")
	public List<CidadeModel> listarPopulacao(){
		return cidadeRepository.findByPopulacao();
	}*/
	
	/**
	 * Inclui uma nova cidade
	 * @param cidadeModel
	 * @return
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CidadeModel adicionarCidade(@Valid @RequestBody CidadeModel cidadeModel) {
		return cidadeService.salvar(cidadeModel);
	}
	
	/**
	 * Atualizar cadastro de cidade
	 */
	@PutMapping("/{id}")
	public ResponseEntity<CidadeModel> atualizarCidade(@Valid @PathVariable Long id, @RequestBody CidadeModel cidadeModel){
		if (!cidadeRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		cidadeModel.setId(id);
		cidadeModel = cidadeService.salvar(cidadeModel);
		return ResponseEntity.ok(cidadeModel);
	}
	
	/**
	 * Excluir cidade
	 */
	public ResponseEntity<Void> exclirCidade(@PathVariable Long id) {
		if (!cidadeRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		cidadeService.exclir(id);
		return ResponseEntity.noContent().build();
	}
}
