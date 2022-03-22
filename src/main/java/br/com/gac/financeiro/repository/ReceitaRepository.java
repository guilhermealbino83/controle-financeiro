package br.com.gac.financeiro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.gac.financeiro.modelo.Receita;

public interface ReceitaRepository extends JpaRepository<Receita, Long>{
	
	@Query("SELECT r FROM Receita r WHERE r.descricao LIKE %:descricao%")
	List<Receita> buscaPorDescricao(@Param("descricao") String descricao);
	
	@Query("SELECT r FROM Receita r WHERE YEAR(dataLancamento) = :ano AND MONTH(dataLancamento) = :mes")
	List<Receita> buscaPorAnoMes(@Param("ano") int ano, @Param("mes") int mes);
	
}
