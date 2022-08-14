package br.com.gac.financeiro.controller.dto;

import java.util.List;

public class ResumoDespesaDto {
	private ResumoItemDto receita;
	private ResumoItemDto despesa;
	private double saldo;
	private List<ResumoDespesaDto> despesasPorCategoria;
	
	public ResumoDespesaDto() {

	}
	
	public void setReceita(ResumoItemDto receita) {
		this.receita = receita;
	}
	public void setDespesa(ResumoItemDto despesa) {
		this.despesa = despesa;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	public void setDespesasPorCategoria(List<ResumoDespesaDto> despesasPorCategoria) {
		this.despesasPorCategoria = despesasPorCategoria;
	}
	
	
}
