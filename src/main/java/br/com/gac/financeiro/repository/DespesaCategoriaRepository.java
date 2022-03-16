package br.com.gac.financeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gac.financeiro.modelo.DespesaCategoria;

public interface DespesaCategoriaRepository extends JpaRepository<DespesaCategoria, Long>{
	
	DespesaCategoria findByDescricao(String descricao);

}
