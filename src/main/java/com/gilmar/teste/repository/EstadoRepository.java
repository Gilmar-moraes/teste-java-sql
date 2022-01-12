package com.gilmar.teste.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gilmar.teste.model.EstadoModel;

@Repository
public interface EstadoRepository extends JpaRepository<EstadoModel, Long>{

	List<EstadoModel> findByNome(String nome);
}
