package br.com.naturaves.cobrancanaturaves.cobranca.application.api;

import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/boleto/{idBoleto}/cobranca")
public interface CobrancaAPI {
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	CobrancaResponse postCobranca(@PathVariable UUID idBoleto, 
			@Valid @RequestBody CobrancaRequest cobrancaRequest);

	@PostMapping(path = "/cadastrar-cobrancas")
	@ResponseStatus(code = HttpStatus.CREATED)
    List<CobrancaListResponse> postCobrancas(@RequestBody @Valid CobrancaListRequest cobrancaListRequestt);
	
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
