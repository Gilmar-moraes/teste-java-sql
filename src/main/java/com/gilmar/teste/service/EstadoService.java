package com.gilmar.teste.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gilmar.teste.exception.NegocioException;
import com.gilmar.teste.model.EstadoModel;
import com.gilmar.teste.repository.EstadoRepository;

import lombok.AllArgsConstructor;


@Service
public class EstadoService {

	@Autowired
	private EstadoRepository estadoRepository;
	
	public EstadoModel buscarEstado(Long estadoId) {
		return estadoRepository.findById(estadoId)
				.orElseThrow(() -> new NegocioException("Estado não encontrado"));
	}
	
	@Transactional
	public EstadoModel salvar(EstadoModel estadoModel) {
		boolean nomeEmUso = estadoRepository.findByNome(estadoModel.getNome())
				.stream()
				.anyMatch(estadoExistente -> !estadoExistente.equals(estadoModel));
		if (nomeEmUso) {
			throw new NegocioException("Já existe este nome de Estado");
		}
		
		return estadoRepository.save(estadoModel);
	}
}
