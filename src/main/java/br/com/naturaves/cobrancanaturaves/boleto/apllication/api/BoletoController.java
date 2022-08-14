package br.com.naturaves.cobrancanaturaves.boleto.apllication.api;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.RestController;

import br.com.naturaves.cobrancanaturaves.boleto.apllication.service.BoletoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequiredArgsConstructor
public class BoletoController implements BoletoAPI {
	private final BoletoService boletoService;

	@Override
	public BoletoResponse postBoleto(UUID idCliente, @Valid BoletoRequest boletoRequest) {
		log.info("[inicia] BoletoController - postBoleto");
		log.info("[idCliente]{}",idCliente);
		BoletoResponse boleto = boletoService.criaBoleto(idCliente,boletoRequest);
		log.info("[finaliza] BoletoController - postBoleto");
		return boleto;
	}
}
