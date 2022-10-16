package br.com.naturaves.cobrancanaturaves.cobranca.application.api;

import java.util.List;
import java.util.UUID;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/boleto/{idBoleto}/cobranca")
public interface CobrancaAPI {
	@PostMapping
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

}
