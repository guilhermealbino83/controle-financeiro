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

import br.com.gac.financeiro.controller.dto.ReceitaDto;
import br.com.gac.financeiro.controller.form.ReceitaForm;
import br.com.gac.financeiro.modelo.Receita;
import br.com.gac.financeiro.repository.ReceitaRepository;

@RestController
@RequestMapping("/receitas")
public class ReceitaController {

	@Autowired
	private ReceitaRepository receitaRepository;

	@GetMapping
	public ResponseEntity<List<ReceitaDto>> lista(@RequestParam(required = false) String descricao) {

		if (descricao == null) {
			return ResponseEntity
					.ok(receitaRepository.findAll().stream().map(ReceitaDto::new).collect(Collectors.toList()));
		} else {
			List<ReceitaDto> lista = receitaRepository.buscaPorDescricao(descricao).stream().map(ReceitaDto::new)
					.collect(Collectors.toList());

			if (lista.isEmpty()) {
				return ResponseEntity.notFound().build();
			} else {
				return ResponseEntity.ok(lista);
			}
		}

	}

	@GetMapping("/{id}")
	public ResponseEntity<ReceitaDto> lista(@PathVariable Long id) {
		Optional<Receita> optional = receitaRepository.findById(id);

		if (optional.isPresent()) {
			return ResponseEntity.ok(new ReceitaDto(optional.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/{ano}/{mes}")
	public ResponseEntity<List<ReceitaDto>> listaPorAnoMes(@PathVariable(name = "ano") @NotNull @Min(1900) @Max(2100) int ano,
			@PathVariable(name="mes") @NotNull @Min(1) @Max(12) int mes) {

		List<ReceitaDto> lista = receitaRepository.buscaPorAnoMes(ano, mes).stream().map(ReceitaDto::new)
				.collect(Collectors.toList());

		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(lista);
		}

	}

	@PostMapping
	@Transactional
	public ResponseEntity<ReceitaDto> cadastar(@RequestBody @Valid ReceitaForm form) {
		Receita receita = form.converteReceita();
		receitaRepository.save(receita);
		return ResponseEntity.ok(new ReceitaDto(receita));
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ReceitaDto> alterar(@PathVariable Long id, @RequestBody @Valid ReceitaForm form) {
		Optional<Receita> optional = receitaRepository.findById(id);

		if (optional.isPresent()) {
			Receita receita = optional.get();
			form.atualizaReceita(receita);
			return ResponseEntity.ok(new ReceitaDto(receita));
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<ReceitaDto> Deletar(@PathVariable Long id) {
		Optional<Receita> optional = receitaRepository.findById(id);

		if (optional.isPresent()) {
			receitaRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
