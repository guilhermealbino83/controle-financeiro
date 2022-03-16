package br.com.gac.financeiro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.gac.financeiro.modelo.Despesa;

public interface DespesaRepository extends JpaRepository<Despesa, Long>{
	
	@Query("SELECT d FROM Despesa d WHERE d.descricao LIKE %:descricao%")
	List<Despesa> buscaPorDescricao(@Param("descricao") String descricao);

}
