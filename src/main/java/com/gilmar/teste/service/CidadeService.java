package com.gilmar.teste.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gilmar.teste.exception.NegocioException;
import com.gilmar.teste.model.CidadeModel;
import com.gilmar.teste.repository.CidadeRepository;

import lombok.AllArgsConstructor;

@Service
public class CidadeService {
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	public CidadeModel buscar(Long clienteId) {
		return cidadeRepository.findById(clienteId)
				.orElseThrow(() -> new NegocioException("Cidade não encontrada"));
	}

	@Transactional
	public CidadeModel salvar(CidadeModel cidadeModel) {
		boolean nomeEmUso = cidadeRepository.findByNome(cidadeModel.getNome())
				.stream()
				.anyMatch(cidadeExistente -> !cidadeExistente.equals(cidadeModel));
		if (nomeEmUso) {
			throw new NegocioException("Já existe esta cidade cadastrada!");
		}
		return cidadeRepository.save(cidadeModel);
	}
	
	@Transactional
	public void exclir(Long cidadeId) {
		cidadeRepository.deleteById(cidadeId);
	}
}
