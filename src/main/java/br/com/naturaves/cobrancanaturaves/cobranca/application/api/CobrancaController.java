package br.com.naturaves.cobrancanaturaves.cobranca.application.api;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.RestController;

import br.com.naturaves.cobrancanaturaves.cobranca.application.service.CobrancaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequiredArgsConstructor
public class CobrancaController implements CobrancaAPI {
	private final CobrancaService cobrancaService;

	@Override
	public CobrancaResponse postCobranca(UUID idBoleto, @Valid CobrancaRequest cobrancaRequest) {
		log.info("[inicia] CobrancaController - postCobranca");
		log.info("[idBoleto]{}", idBoleto);
		CobrancaResponse cobranca = cobrancaService.criaCobranca(idBoleto, cobrancaRequest);
		log.info("[finaliza] CobrancaController - postCobranca");
		return cobranca;
	}

	@Override
	public List<CobrancaBoletoListResponse> getCobrancaDoBoletoComId(UUID idBoleto) {
		log.info("[inicia] CobrancaController - getCobrancaDoBoletoComId");
		log.info("[idBoleto]{}", idBoleto);
		List<CobrancaBoletoListResponse> cobrancaDoBoleto = cobrancaService.buscaCobrancaDoBoletoComId(idBoleto);
		log.info("[finaliza] CobrancaController - getCobrancaDoBoletoComId");
		return cobrancaDoBoleto;
	}

	@Override
	public CobrancaDetalhadoResponse getCobrancaAtravesId(UUID idBoleto, UUID idCobranca) {
		log.info("[inicia] CobrancaController - getCobrancaAtravesId");
		log.info("[idBoleto]{} - [idCobranca]{}", idBoleto, idCobranca);
		CobrancaDetalhadoResponse cobranca = cobrancaService.buscaCobrancaDoBoletoComId(idBoleto, idCobranca);
		log.info("[finaliza] CobrancaController - getCobrancaAtravesId");
		return cobranca;
	}

	@Override
	public void deletaCobrancaDoBoletoComId(UUID idBoleto, UUID idCobranca) {
		log.info("[inicia] CobrancaController - deletaCobrancaDoBoletoComId");
		log.info("[idBoleto]{} - [idCobranca]{}", idBoleto, idCobranca);
		cobrancaService.deletaCobrancaDoBoletoComId(idBoleto, idCobranca);
		log.info("[finaliza] CobrancaController - deletaCobrancaDoBoletoComId");
	}

	@Override
	public void patchCobranca(UUID idBoleto, UUID idCobranca,
			@Valid CobrancaAlteracaoRequest cobrancaAlteracaoRequest) {
		log.info("[inicia] CobrancaController - patchCobranca");
		log.info("[idBoleto]{} - [idCobranca]{}", idBoleto, idCobranca);
		cobrancaService.alteraCobrancaDoBoletoComId(idBoleto, idCobranca, cobrancaAlteracaoRequest);
		log.info("[finaliza] CobrancaController - patchCobranca");
	}

	@Override
	public List<CobrancaPorDateListResponse> buscaCobrancasPorDataDeRetorno(LocalDate dataDeRetorno) {
		log.info("[inicia] CobrancaController - buscaCobrancaPorDataDeRetorno");
		log.info("[dataDeRetorno]{}", dataDeRetorno);
		List<CobrancaPorDateListResponse> listaCobranca = cobrancaService.buscaCobrancasPorDataDeRetorno(dataDeRetorno);
		log.info("[finaliza] CobrancaController - buscaCobrancaPorDataDeRetorno");
		return listaCobranca;
	}

	@Override
	public CobrancaAndClienteDetalhadoResponse buscaCobrancaDetalhada(UUID idCliente, UUID idBoleto, UUID idCobranca) {
		log.info("[inicia] CobrancaController - buscaCobrancaDetalhada");
		log.info("[idCliente]{} - [idBoleto]{} - [idCobranca]{}", idCliente, idBoleto, idCobranca);
		CobrancaAndClienteDetalhadoResponse cobranca = cobrancaService.buscaTodasCobrancasDoCliente(idCliente, idBoleto,
				idCobranca);
		log.info("[inicia] CobrancaController - buscaCobrancaDetalhada");
		return cobranca;
	}
}