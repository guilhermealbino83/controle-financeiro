package br.com.gac.financeiro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.gac.financeiro.modelo.Despesa;
import br.com.gac.financeiro.modelo.projecao.ResumoCategoriaDespesaDto;
import br.com.gac.financeiro.modelo.projecao.ResumoItemPrj;

public interface DespesaRepository extends JpaRepository<Despesa, Long> {

	@Query("SELECT d FROM Despesa d WHERE d.descricao LIKE %:descricao%")
	List<Despesa> buscaPorDescricao(@Param("descricao") String descricao);

	@Query("SELECT d FROM Despesa d WHERE YEAR(d.dataLancamento) = :ano AND MONTH(d.dataLancamento) = :mes")
	List<Despesa> buscaPorAnoMes(@Param("ano") int ano, @Param("mes") int mes);
	
	
	@Query(value = "SELECT 'DESPESA' mensagem, "
			+ " DATE_FORMAT(d.data_lancamento,'%Y-%m') mesReferencia, "
			+ " sum(d.valor) valor"
			+ " FROM despesas d "
			+ " WHERE YEAR(d.data_lancamento) = :ano "
			+ " AND MONTH(d.data_lancamento) = :mes"
			+ " GROUP BY DATE_FORMAT(data_lancamento,'%Y-%m')", nativeQuery = true)
	
	List<ResumoItemPrj> resumoCompletoPorAnoMes(@Param("ano") int ano, @Param("mes") int mes);

	@Query(value = "SELECT sum(d.valor) valor"
			+ " FROM despesas d "
			+ " WHERE YEAR(d.data_lancamento) = :ano "
			+ " AND MONTH(d.data_lancamento) = :mes", nativeQuery = true)
	double resumoPorAnoMes(@Param("ano") int ano, @Param("mes") int mes);
	
	@Query(value = "SELECT c.descricao, "
			+ " sum(d.valor) total "
			+ " FROM despesas d, "
			+ " desp_categorias c"
			+ " WHERE c.id = d.categoria_id"
			+ " AND YEAR(d.data_lancamento) = :ano "
			+ " AND MONTH(d.data_lancamento) = :mes "
			+ " GROUP BY c.descricao", nativeQuery = true)
	List<ResumoCategoriaDespesaDto> resumoCategoriaPorAnoMes(@Param("ano") int ano, @Param("mes") int mes);
	
}
