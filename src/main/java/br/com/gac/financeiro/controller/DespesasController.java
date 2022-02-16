package br.com.gac.financeiro.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gac.financeiro.controller.dto.DespesaDto;
import br.com.gac.financeiro.controller.form.DespesaForm;
import br.com.gac.financeiro.modelo.Despesa;
import br.com.gac.financeiro.repository.DespesaRepository;

@RestController
@RequestMapping("/despesas")
public class DespesasController {

	@Autowired
	private DespesaRepository despesaRepository;

	@GetMapping
	public ResponseEntity<List<DespesaDto>> lista() {
		return ResponseEntity
				.ok(despesaRepository.findAll().stream().map(DespesaDto::new).collect(Collectors.toList()));
	}

	@GetMapping("/{id}")
	public ResponseEntity<DespesaDto> lista(@PathVariable Long id) {
		Optional<Despesa> optional = despesaRepository.findById(id);

		if (optional.isPresent()) {
			return ResponseEntity.ok().body(new DespesaDto(optional.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@Transactional
	public ResponseEntity<DespesaDto> cadastrar(@RequestBody @Valid DespesaForm form) {
		Despesa despesa = form.converter();
		despesaRepository.save(despesa);
		return ResponseEntity.ok().body(new DespesaDto(despesa));

	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<DespesaDto> lista(@PathVariable Long id, @RequestBody @Valid DespesaForm form) {
		Optional<Despesa> optional = despesaRepository.findById(id);

		if (optional.isPresent()) {
			Despesa despesa = optional.get();
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
