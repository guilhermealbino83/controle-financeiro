package br.com.gac.financeiro.controller.dto;

import java.util.List;

import br.com.gac.financeiro.modelo.projecao.ResumoCategoriaDespesaDto;

public class ResumoMesDto {
	
	private double totalReceitas;
	private double totalDespesas;
	private double saldo;
	private List<ResumoCategoriaDespesaDto> totalPorCategoria;
	
	public ResumoMesDto(double totalReceitas, double totalDespesas) {
		this.totalReceitas = totalReceitas;
		this.totalDespesas = totalDespesas;
		atualizaSaldo();
	}

	public double getTotalReceitas() {
		return totalReceitas;
	}

	public void setTotalReceitas(double totalReceitas) {
		this.totalReceitas = totalReceitas;
		atualizaSaldo();
	}

	public double getTotalDespesas() {
		return totalDespesas;
	}

	public void setTotalDespesas(double totalDespesas) {
		this.totalDespesas = totalDespesas;
		atualizaSaldo();
	}

	public List<ResumoCategoriaDespesaDto> getTotalPorCategoria() {
		return totalPorCategoria;
	}

	public void setTotalPorCategoria(List<ResumoCategoriaDespesaDto> totalPorCategoria) {
		this.totalPorCategoria = totalPorCategoria;
	}

	public double getSaldo() {
		return saldo;
	}
	
    private void atualizaSaldo() {
    	this.saldo = this.totalReceitas- this.totalDespesas; 
    }	

}
