package br.com.naturaves.cobrancanaturaves.cobranca.application.api;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/cobranca")
public interface CobrancaAPI {

	@PostMapping(value = "boleto/{idBoleto}")
	@ResponseStatus(code = HttpStatus.CREATED)
	CobrancaResponse postCobranca(@PathVariable UUID idBoleto,
								  @Valid @RequestBody CobrancaRequest cobrancaRequest);

	@GetMapping(value = "/boleto/{idBoleto}")
	@ResponseStatus(code = HttpStatus.OK)
	List<CobrancaBoletoListResponse> getCobrancaDoBoletoComId(@PathVariable UUID idBoleto);

	@GetMapping(value = "{idCobranca}/boleto/{idBoleto}")
	@ResponseStatus(code = HttpStatus.OK)
	CobrancaDetalhadoResponse getCobrancaAtravesId(@PathVariable UUID idBoleto,
												   @PathVariable UUID idCobranca);
	
	@GetMapping(value = "{idCliente}/cobranca/{idBoleto}cobranca/{idCobranca}")
	@ResponseStatus(code = HttpStatus.OK)
	CobrancaAndClienteDetalhadoResponse buscaCobrancaDetalhada(@PathVariable UUID idCliente,
			@PathVariable UUID idBoleto,@PathVariable UUID idCobranca);

	@DeleteMapping(value = "/{idCobranca}/boleto/{idBoleto}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	void deletaCobrancaDoBoletoComId(@PathVariable UUID idBoleto,
									 @PathVariable UUID idCobranca);

	@PatchMapping(value = "/{idCobranca}/boleto/{idBoleto}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	void patchCobranca(@PathVariable UUID idBoleto,
					   @PathVariable UUID idCobranca,
					   @Valid @RequestBody CobrancaAlteracaoRequest cobrancaAlteracaoRequest);


	@GetMapping(value = "/findByDate" )
	@ResponseStatus(code = HttpStatus.OK)
	List<CobrancaPorDateListResponse> buscaCobrancasPorDataDeRetorno(
			@RequestParam(value = "dataDeRetorno")
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataDeRetorno);
}
