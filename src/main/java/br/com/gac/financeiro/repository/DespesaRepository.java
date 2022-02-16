package br.com.gac.financeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gac.financeiro.modelo.Despesa;

public interface DespesaRepository extends JpaRepository<Despesa, Long>{

}
