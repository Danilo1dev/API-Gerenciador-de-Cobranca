package br.com.naturaves.cobrancanaturaves.boleto.application.api;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/v1/cliente/{idCliente}/boleto")
public interface BoletoAPI {
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	BoletoResponse postBoleto(@PathVariable UUID idCliente, @Valid @RequestBody BoletoRequest boletoRequest);

	@PostMapping(path = "/cadastro-boletos")
	@ResponseStatus(code = HttpStatus.CREATED)
	ResponseEntity<Void> postBoletos(@RequestBody @Valid BoletoListRequest boletoListRequest);
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	List<BoletoClienteListResponse> getBoletoDoClienteComId(@PathVariable UUID idCliente);

	@GetMapping(value = "/{idBoleto}")
	@ResponseStatus(code = HttpStatus.OK)
	BoletoDetalhadoResponse getBoletoAtravesId(@PathVariable UUID idCliente, @PathVariable UUID idBoleto);

	@DeleteMapping(value = "/{idBoleto}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	void deletaBoletoDoClienteComId(@PathVariable UUID idCliente, @PathVariable UUID idBoleto);

	@PatchMapping(value = "/{idBoleto}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	void patchBoleto(@PathVariable UUID idCliente, @PathVariable UUID idBoleto,
			@Valid @RequestBody BoletoAlteracaoRequest boletoAlteracaoRequest);
}
