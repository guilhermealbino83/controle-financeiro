package br.com.gac.financeiro.controller.form;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.gac.financeiro.modelo.Receita;

public class ReceitaForm {

	@NotNull @NotEmpty
	private String descricao;
	@NotNull 
	private Double valor;
	@NotNull
	private LocalDate dataLancamento;

	public ReceitaForm() {
		// TODO Auto-generated constructor stub
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public LocalDate getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(LocalDate dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public Receita converteReceita() {
		Receita receita = new Receita(this.descricao, this.valor, this.dataLancamento);
		return receita;
	}

	public void atualizaReceita(Receita receita) {
		receita.setDescricao(this.descricao);
		receita.setValor(this.valor);
		receita.setDataLancamento(this.dataLancamento);
	}

}
