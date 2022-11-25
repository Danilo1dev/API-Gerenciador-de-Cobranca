package br.com.naturaves.cobrancanaturaves.cobranca.application.api;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/cobranca")
public interface CobrancaAPI {
	
	@PostMapping(value = "/{idBoleto}/idBoleto")
	@ResponseStatus(code = HttpStatus.CREATED)
	CobrancaResponse postCobranca(@PathVariable UUID idBoleto, 
			@Valid @RequestBody CobrancaRequest cobrancaRequest);
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	List<CobrancaBoletoListResponse> getCobrancaDoBoletoComId(@PathVariable UUID idBoleto);
	
	@GetMapping(value = "/{idCobranca}")
	@ResponseStatus(code = HttpStatus.OK)
	CobrancaDetalhadoResponse getCobrancaAtravesId(@PathVariable UUID idBoleto, @PathVariable UUID idCobranca);
	
	@DeleteMapping(value = "/{idCobranca}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	void deletaCobrancaDoBoletoComId(@PathVariable UUID idBoleto, @PathVariable UUID idCobranca);
	
	@PatchMapping(value = "/{idCobranca}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	void patchCobranca(@PathVariable UUID idBoleto,@PathVariable UUID idCobranca,
			@Valid @RequestBody CobrancaAlteracaoRequest cobrancaAlteracaoRequest);

	@GetMapping(value = "/findByDate" )
	@ResponseStatus(code = HttpStatus.OK)
	List<CobrancaPorDateListResponse> buscaCobrancasPorDataDeRetorno(
									@RequestParam(value = "dataDeRetorno")
									@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataDeRetorno);
}
