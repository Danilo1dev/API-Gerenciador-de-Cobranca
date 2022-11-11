package br.com.naturaves.cobrancanaturaves.boleto.application.api;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.naturaves.cobrancanaturaves.boleto.application.service.BoletoService;
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
		log.info("[idCliente]{}", idCliente);
		BoletoResponse boleto = boletoService.criaBoleto(idCliente, boletoRequest);
		log.info("[finaliza] BoletoController - postBoleto");
		return boleto;
	}

	@Override
	public List<BoletoListResponse> postBoletos(@RequestBody @Valid BoletoListRequest boletoListRequest) {
		log.info("[inicia] BoletoController - postBoletos");
		List<BoletoListResponse> boletoListResponse = boletoService.criaListaBoletos(boletoListRequest.getData());
		log.info("[inicia] BoletoController - postBoletos");
		return boletoListResponse;
	}

	@Override
	public List<BoletoClienteListResponse> getBoletoDoClienteComId(UUID idCliente) {
		log.info("[inicia] BoletoController - getBoletoDoClienteComId");
		log.info("[idCliente]{}", idCliente);
		List<BoletoClienteListResponse> boletoDoCliente = boletoService.buscaBoletoDoClienteComId(idCliente);
		log.info("[finaliza] BoletoController - getBoletoDoClienteComId");
		return boletoDoCliente;
	}

	@Override
	public BoletoDetalhadoResponse getBoletoAtravesId(UUID idCliente, UUID idBoleto) {
		log.info("[inicia] BoletoController - getBoletoAtravesId");
		log.info("[idCliente]{} - [idBoleto] {}", idCliente, idBoleto);
		BoletoDetalhadoResponse boleto = boletoService.buscaBoletoDoClienteComId(idCliente, idBoleto);
		log.info("[finaliza] BoletoController - getBoletoAtravesId");
		return boleto;
	}

	@Override
	public void deletaBoletoDoClienteComId(UUID idCliente, UUID idBoleto) {
		log.info("[inicia] BoletoController - deletaBoletoAtravesId");
		log.info("[idCliente]{} - [idBoleto] {}", idCliente, idBoleto);
		boletoService.deletaBoletoDoClienteComId(idCliente, idBoleto);
		log.info("[finaliza] BoletoController - deletaBoletoAtravesId");
	}

	@Override
	public void patchBoleto(UUID idCliente, UUID idBoleto, @Valid BoletoAlteracaoRequest boletoAlteracaoRequest) {
		log.info("[inicia] BoletoController - patchBoleto");
		log.info("[idCliente]{} - [idBoleto] {}", idCliente, idBoleto);
		boletoService.alteraBoletoDoClienteComId(idCliente, idBoleto, boletoAlteracaoRequest);
		log.info("[finaliza] BoletoController - patchBoleto");
	}

}
