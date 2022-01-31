package br.com.gac.financeiro.controller.dto;

import java.time.LocalDate;

import br.com.gac.financeiro.modelo.Receita;

public class ReceitaDto {

	private String descricao;
	private Double valor;
	private LocalDate dataLancamento;

	public ReceitaDto(Receita receita) {

		this.descricao = receita.getDescricao();
		this.valor = receita.getValor();
		this.dataLancamento = receita.getDataLancamento();

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

}
