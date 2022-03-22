package br.com.gac.financeiro.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.gac.financeiro.controller.dto.DespesaDto;
import br.com.gac.financeiro.controller.dto.ReceitaDto;
import br.com.gac.financeiro.controller.form.DespesaForm;
import br.com.gac.financeiro.modelo.Despesa;
import br.com.gac.financeiro.modelo.DespesaCategoria;
import br.com.gac.financeiro.repository.DespesaRepository;
import br.com.gac.financeiro.repository.DespesaCategoriaRepository;

@RestController
@RequestMapping("/despesas")
public class DespesasController {

	@Autowired
	private DespesaRepository despesaRepository;
	@Autowired
	private DespesaCategoriaRepository despesaCategoriaRepository;

	private String categoriaPadrao = "Outras";

	private DespesaCategoria defineCategoria(String descricao) {

		DespesaCategoria categoria;

		if (descricao == null) {
			categoria = despesaCategoriaRepository.findByDescricao(categoriaPadrao);
		} else {
			categoria = despesaCategoriaRepository.findByDescricao(descricao);
		}

		if (categoria == null) {
			categoria = despesaCategoriaRepository.findByDescricao(categoriaPadrao);
		}

		return categoria;
	}

	@GetMapping("/{id}")
	public ResponseEntity<DespesaDto> lista(@PathVariable(required = false) Long id) {
		Optional<Despesa> optional = despesaRepository.findById(id);

		if (optional.isPresent()) {
			return ResponseEntity.ok().body(new DespesaDto(optional.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping
	public ResponseEntity<List<DespesaDto>> lista(@RequestParam(required = false) String descricao) {
		if (descricao==null) {
			return ResponseEntity
					.ok(despesaRepository.findAll().stream().map(DespesaDto::new).collect(Collectors.toList()));
		} else {
			List<DespesaDto> despesas = despesaRepository.buscaPorDescricao(descricao).stream().map(DespesaDto::new)
					.collect(Collectors.toList());

			if (!despesas.isEmpty()) {
				return ResponseEntity.ok(despesas);
			}
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/{ano}/{mes}")
	public ResponseEntity<List<DespesaDto>> listaPorAnoMes(@PathVariable(name = "ano") @NotNull @Min(1900) @Max(2100) int ano,
			@PathVariable(name="mes") @NotNull @Min(1) @Max(12) int mes) {

		List<DespesaDto> lista = despesaRepository.buscaPorAnoMes(ano, mes).stream().map(DespesaDto::new)
				.collect(Collectors.toList());

		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(lista);
		}

	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<DespesaDto> cadastrar(@RequestBody @Valid DespesaForm form) {

		Despesa despesa = form.converter();
		despesa.setCategoria(defineCategoria(form.getCategoria()));
		despesaRepository.save(despesa);

		return ResponseEntity.ok().body(new DespesaDto(despesa));

	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<DespesaDto> lista(@PathVariable Long id, @RequestBody @Valid DespesaForm form) {
		Optional<Despesa> optional = despesaRepository.findById(id);

		if (optional.isPresent()) {
			Despesa despesa = optional.get();
			despesa.setCategoria(defineCategoria(form.getCategoria()));
			form.atualiza(despesa);
			return ResponseEntity.ok().body(new DespesaDto(optional.get()));
		}

		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<DespesaDto> Deletar(@PathVariable Long id) {
		Optional<Despesa> optional = despesaRepository.findById(id);

		if (optional.isPresent()) {
			despesaRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
