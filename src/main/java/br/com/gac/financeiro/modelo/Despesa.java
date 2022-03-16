package br.com.gac.financeiro.modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "despesas")
public class Despesa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String descricao;
	private Double valor;
	private LocalDateTime dataCriacao;
	private LocalDate dataLancamento;
	@ManyToOne
	private DespesaCategoria categoria;

	public Despesa() {
		// Contrutor padão para o SP JPA
	}

	public Despesa(String descricao, Double valor, LocalDate dataLancamento, DespesaCategoria categoria) {
		this.descricao = descricao;
		this.valor = valor;
		this.dataCriacao = LocalDateTime.now();
		this.dataLancamento = dataLancamento;
		this.categoria = categoria;
	}
	
	public Despesa(String descricao, Double valor, LocalDate dataLancamento) {
		this.descricao = descricao;
		this.valor = valor;
		this.dataCriacao = LocalDateTime.now();
		this.dataLancamento = dataLancamento;		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public LocalDate getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(LocalDate dataLancamento) {
		this.dataLancamento = dataLancamento;
	}
	
	

	public DespesaCategoria getCategoria() {
		return categoria;
	}

	public void setCategoria(DespesaCategoria categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return "descricao["+this.descricao+"]";
	}
}
