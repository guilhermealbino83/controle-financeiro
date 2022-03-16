package br.com.gac.financeiro.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "desp_categorias")
public class DespesaCategoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String descricao;

	public DespesaCategoria() {

	}

	public Long getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

}
