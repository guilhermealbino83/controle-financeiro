package br.com.gac.financeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gac.financeiro.modelo.Receita;

public interface ReceitaRepository extends JpaRepository<Receita, Long>{

}
