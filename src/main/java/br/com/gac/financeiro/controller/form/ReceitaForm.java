package br.com.gac.financeiro.controller.form;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import br.com.gac.financeiro.modelo.Receita;

public class ReceitaForm {

	@NotNull(message = "Descricao deve ser preenchida")
	@Size(min = 5, max = 2000, message = "Descricao deve ter entre 5 a 2000 caracteres")
	private String descricao;

	@NotNull(message = "Valor deve ser preenchido")
	@Positive(message = "Valor precisa ser positivo maior que zero")
	private Double valor;

	@NotNull(message = "Data de Lançamento deve ser preenchida")
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
		return new Receita(this.descricao, this.valor, this.dataLancamento);
	}

	public void atualizaReceita(Receita receita) {
		receita.setDescricao(this.descricao);
		receita.setValor(this.valor);
		receita.setDataLancamento(this.dataLancamento);
	}

}
