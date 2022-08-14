package br.com.gac.financeiro.controller;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gac.financeiro.controller.dto.ResumoMesDto;
import br.com.gac.financeiro.repository.DespesaRepository;
import br.com.gac.financeiro.repository.ReceitaRepository;

@RestController
@RequestMapping("/resumo")
public class ResumoController {
	
	@Autowired
	DespesaRepository despesaRepository;

	@Autowired
	ReceitaRepository receitaRepository;
	
	@GetMapping("/{ano}/{mes}")
	public ResponseEntity<ResumoMesDto> resumoAnoMes(@PathVariable(name = "ano") @NotNull @Min(1900) @Max(2100) int ano,
			@PathVariable(name="mes") @NotNull @Min(1) @Max(12) int mes) {
		
		ResumoMesDto resumo = new ResumoMesDto(receitaRepository.resumoPorAnoMes(ano, mes) , despesaRepository.resumoPorAnoMes(ano, mes));
		
		resumo.setTotalPorCategoria(despesaRepository.resumoCategoriaPorAnoMes(ano, mes));
		
		return ResponseEntity.ok(resumo);  
	}

}
