package br.com.naturaves.cobrancanaturaves.boleto.application.api;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/titulos")
public interface TituloApi {
	

	@PatchMapping(value = "/{idTitulo}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	void patchTitulo(@PathVariable UUID idTitulo);
}
