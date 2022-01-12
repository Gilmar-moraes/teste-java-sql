package com.gilmar.teste.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gilmar.teste.model.CidadeModel;

@Repository
public interface CidadeRepository extends JpaRepository<CidadeModel, Long>{

	List<CidadeModel> findByNome(String nome);
	
	@Query(value = "SELECT"
			+ "COUNT(cidades.nome) AS Qtd_Cidades,"
			+ "estados.uf"
			+ "FROM teste.estados"
			+ "inner join"
			+ "cidades on estados.id_Estados = cidades.codigo_estado"
			+ "WHERE cidades.codigo_estado = ?1;", nativeQuery = true)
	List<CidadeModel> findByCidadeUf(Long codigoEstado);
	
	@Query(value = "SELECT "
			+ "sum(cidades.area) AS Qtd_Area, "
			+ "estados.uf "
			+ "FROM teste.estados "
			+ "inner join "
			+ "cidades on estados.id_Estados = cidades.codigo_estado WHERE cidades.codigo_estado = ?1 ;", nativeQuery = true)
	List<CidadeModel> findByAreaPorUf(Long codigoEstado);
	
	@Query(name = "select count(cidades.nome) from teste.cidades where populacao>10.000", nativeQuery = true)
	List<CidadeModel> findByPopulacaoGrande(double populacao);
}