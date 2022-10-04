package br.com.naturaves.cobrancanaturaves.boleto.application.api;

import java.util.List;
import java.util.UUID;
import javax.validation.Valid;

import br.com.naturaves.cobrancanaturaves.handler.APIException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import br.com.naturaves.cobrancanaturaves.boleto.application.service.BoletoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.multipart.MultipartFile;

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

	@Override
	public List<BoletoClienteListResponse> getBoletoDoClienteComId(UUID idCliente) {
		log.info("[inicia] BoletoController - getBoletoDoClienteComId");
		log.info("[idCliente]{}",idCliente);
		List<BoletoClienteListResponse>boletoDoCliente = boletoService.buscaBoletoDoClienteComId(idCliente);
		log.info("[finaliza] BoletoController - getBoletoDoClienteComId");
		return boletoDoCliente;
	}

	@Override
	public BoletoDetalhadoResponse getBoletoAtravesId(UUID idCliente, UUID idBoleto) {
		log.info("[inicia] BoletoController - getBoletoAtravesId");
		log.info("[idCliente]{} - [idBoleto] {}",idCliente, idBoleto);
		BoletoDetalhadoResponse boleto = boletoService.buscaBoletoDoClienteComId(idCliente, idBoleto);
		log.info("[finaliza] BoletoController - getBoletoAtravesId");
		return boleto;
	}

	@Override
	public void deletaBoletoDoClienteComId(UUID idCliente, UUID idBoleto) {
		log.info("[inicia] BoletoController - deletaBoletoAtravesId");
		log.info("[idCliente]{} - [idBoleto] {}",idCliente, idBoleto);
		boletoService.deletaBoletoDoClienteComId(idCliente, idBoleto);
		log.info("[finaliza] BoletoController - deletaBoletoAtravesId");	
	}

	@Override
	public void patchBoleto(UUID idCliente, UUID idBoleto,
			@Valid BoletoAlteracaoRequest boletoAlteracaoRequest) {
		log.info("[inicia] BoletoController - patchBoleto");
		log.info("[idCliente]{} - [idBoleto] {}",idCliente, idBoleto);
		boletoService.alteraBoletoDoClienteComId(idCliente, idBoleto, boletoAlteracaoRequest);
		log.info("[finaliza] BoletoController - patchBoleto");
	}

	@Override
	public List<BoletoResponse> postBoletoAtravesCsv(@RequestParam(value = "arquivo_csv") MultipartFile arquivo) {
		log.info("[inicia] BoletoController - postBoletoAtravesCsv");

		if (!arquivo.getOriginalFilename().matches("^.*csv$")) {
			throw APIException.build(HttpStatus.BAD_REQUEST, "Por favor insira um arquivo do tipo csv.");
		}

		List<BoletoResponse> boletos = boletoService.criaBoletoAtravesCsv(arquivo);

		log.info("[finaliza] BoletoController - postBoletoAtravesCsv");
		return null;
	}
}
