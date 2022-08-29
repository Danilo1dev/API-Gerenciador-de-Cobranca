package br.com.naturaves.cobrancanaturaves.boleto.apllication.api;

import java.util.UUID;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
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
	BoletoResponse postBoleto(@PathVariable UUID idCliente,
			@Valid @RequestBody BoletoRequest boletoRequest);
}
