package br.com.gac.financeiro.controller.dto;

public class ResumoCategoriaDespesaDto {
	private String descricao;
	private double total;
	
	public ResumoCategoriaDespesaDto() {
		// TODO Auto-generated constructor stub
	}

	public ResumoCategoriaDespesaDto(String descricao, double total) {
		this.descricao = descricao;
		this.total = total;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
}
