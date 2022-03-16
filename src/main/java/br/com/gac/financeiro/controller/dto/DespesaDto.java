package br.com.gac.financeiro.controller.dto;

import java.time.LocalDate;

import br.com.gac.financeiro.modelo.Despesa;

public class DespesaDto {

	private String descricao;
	private Double valor;
	private LocalDate dataLancamento;
	private String categoria;

	public DespesaDto(Despesa despesa) {
		this.descricao = despesa.getDescricao();
		this.valor = despesa.getValor();
		this.dataLancamento = despesa.getDataLancamento();
		this.categoria = despesa.getCategoria().getDescricao();
	}

	public String getDescricao() {
		return descricao;
	}

	public Double getValor() {
		return valor;
	}

	public LocalDate getDataLancamento() {
		return dataLancamento;
	}

	public String getCategoria() {
		return categoria;
	}

}
