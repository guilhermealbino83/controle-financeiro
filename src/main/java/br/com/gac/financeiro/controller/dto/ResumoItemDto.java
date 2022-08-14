package br.com.gac.financeiro.controller.dto;

public class ResumoItemDto {

	private String mensagem;
	private String mesReferencia;
	private Double valor;

	public ResumoItemDto() {

	}

	public ResumoItemDto(String mensagem, String mesReferencia, Double valor) {
		this.mensagem = mensagem;
		this.mesReferencia = mesReferencia;
		this.valor = valor;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public void setMesReferencia(String mesReferencia) {
		this.mesReferencia = mesReferencia;
	}

	public String getMensagem() {
		return mensagem;
	}

	public String getMesReferencia() {
		return mesReferencia;
	}

	public Double getValor() {
		return valor;
	}

}
