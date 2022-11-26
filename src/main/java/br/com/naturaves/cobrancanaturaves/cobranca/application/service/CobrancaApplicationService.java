package br.com.naturaves.cobrancanaturaves.cobranca.application.service;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import br.com.naturaves.cobrancanaturaves.boleto.application.service.BoletoService;
import br.com.naturaves.cobrancanaturaves.cliente.application.service.ClienteService;
import br.com.naturaves.cobrancanaturaves.cobranca.application.api.CobrancaAlteracaoRequest;
import br.com.naturaves.cobrancanaturaves.cobranca.application.api.CobrancaBoletoListResponse;
import br.com.naturaves.cobrancanaturaves.cobranca.application.api.CobrancaDetalhadoResponse;
import br.com.naturaves.cobrancanaturaves.cobranca.application.api.CobrancaRequest;
import br.com.naturaves.cobrancanaturaves.cobranca.application.api.CobrancaResponse;
import br.com.naturaves.cobrancanaturaves.cobranca.application.api.CobrancaTituloListResponse;
import br.com.naturaves.cobrancanaturaves.cobranca.application.repository.CobrancaRepository;
import br.com.naturaves.cobrancanaturaves.cobranca.domain.Cobranca;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class CobrancaApplicationService implements CobrancaService {
	private final BoletoService boletoService;
	private final CobrancaRepository cobrancaRepository;
	private final ClienteService clienteService;

	@Override
	public CobrancaResponse criaCobranca(UUID idBoleto, @Valid CobrancaRequest cobrancaRequest) {
		log.info("[inicia] CobrancaApplicationService - criaCobranca");
		boletoService.buscaBoletoComIdBoleto(idBoleto);
		Cobranca novaCobranca = new Cobranca(idBoleto, cobrancaRequest);
		Cobranca cobranca = cobrancaRepository.salvaCobranca(novaCobranca);
		log.info("[finaliza] CobrancaApplicationService - criaCobranca");
		return new CobrancaResponse(cobranca.getIdCobranca());
	}

	@Override
	public List<CobrancaBoletoListResponse> buscaCobrancaDoBoletoComId(UUID idBoleto) {
		log.info("[inicia] CobrancaApplicationService - buscaCobrancaDoBoletoComId");
		boletoService.buscaBoletoComIdBoleto(idBoleto);
		List<Cobranca> cobrancaDoBoleto = cobrancaRepository.buscaCobrancaDoBoletoComId(idBoleto);
		log.info("[finaliza] CobrancaApplicationService - buscaCobrancaDoBoletoComId");
		return CobrancaBoletoListResponse.converte(cobrancaDoBoleto);
	}

	@Override
	public List<CobrancaTituloListResponse> buscaCobrancaDoCliente(UUID idCliente) {
		log.info("[inicia] CobrancaApplicationService - buscaCobrancaDoCliente");
		clienteService.buscaClienteAtravesID(idCliente);
		List<Cobranca> cobrancaTitulo = cobrancaRepository.buscaCobrancaDoCliente(idCliente);
		log.info("[finaliza] CobrancaApplicationService - buscaCobrancaDoCliente");		
		return CobrancaTituloListResponse.converte(cobrancaTitulo);
	}

	@Override
	public CobrancaDetalhadoResponse buscaCobrancaDoBoletoComId(UUID idBoleto, UUID idCobranca) {
		log.info("[inicia] CobrancaApplicationService - buscaCobrancaDoBoletoComId");
		boletoService.buscaBoletoComIdBoleto(idBoleto);
		Cobranca cobranca = cobrancaRepository.buscaCobrancaComId(idCobranca);
		log.info("[finaliza] CobrancaApplicationService - buscaCobrancaDoBoletoComId");
		return new CobrancaDetalhadoResponse(cobranca);
	}

	@Override
	public void deletaCobrancaDoBoletoComId(UUID idBoleto, UUID idCobranca) {
		log.info("[inicia] CobrancaApplicationService - deletaCobrancaDoBoletoComId");
		boletoService.buscaBoletoComIdBoleto(idBoleto);
		Cobranca cobranca = cobrancaRepository.buscaCobrancaComId(idCobranca);
		cobrancaRepository.deletaCobranca(cobranca);
		log.info("[finaliza] CobrancaApplicationService - deletaCobrancaDoBoletoComId");
	}

	@Override
	public void alteraCobrancaDoBoletoComId(UUID idBoleto, UUID idCobranca,
			CobrancaAlteracaoRequest cobrancaAlteracaoRequest) {
		log.info("[inicia] CobrancaApplicationService - alteraCobrancaDoBoletoComId");
		boletoService.buscaBoletoComIdBoleto(idBoleto);
		Cobranca cobranca = cobrancaRepository.buscaCobrancaComId(idCobranca);
		cobranca.altera(cobrancaAlteracaoRequest);
		cobrancaRepository.salvaCobranca(cobranca);
		log.info("[finaliza] CobrancaApplicationService - alteraCobrancaDoBoletoComId");
	}

}