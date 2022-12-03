package br.com.naturaves.cobrancanaturaves.cobranca.application.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import br.com.naturaves.cobrancanaturaves.boleto.application.repository.BoletoRepository;
import br.com.naturaves.cobrancanaturaves.boleto.application.service.BoletoService;
import br.com.naturaves.cobrancanaturaves.boleto.domain.Boleto;
import br.com.naturaves.cobrancanaturaves.cliente.application.repository.ClienteRepository;
import br.com.naturaves.cobrancanaturaves.cliente.domain.Cliente;
import br.com.naturaves.cobrancanaturaves.cobranca.application.api.CobrancaAlteracaoRequest;
import br.com.naturaves.cobrancanaturaves.cobranca.application.api.CobrancaAndClienteDetalhadoResponse;
import br.com.naturaves.cobrancanaturaves.cobranca.application.api.CobrancaBoletoListResponse;
import br.com.naturaves.cobrancanaturaves.cobranca.application.api.CobrancaDetalhadoResponse;
import br.com.naturaves.cobrancanaturaves.cobranca.application.api.CobrancaPorDateListResponse;
import br.com.naturaves.cobrancanaturaves.cobranca.application.api.CobrancaRequest;
import br.com.naturaves.cobrancanaturaves.cobranca.application.api.CobrancaResponse;
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
	private final ClienteRepository clienteRepository;
	private final BoletoRepository boletoRepository;

	@Override
	public CobrancaResponse criaCobranca(UUID idBoleto, @Valid CobrancaRequest cobrancaRequest) {
		log.info("[inicia] CobrancaApplicationService - criaCobranca");
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

	@Override
	public List<CobrancaPorDateListResponse> buscaCobrancasPorDataDeRetorno(LocalDate dataDeRetorno) {
		log.info("[inicia] CobrancaApplicationService - buscaCobrancasPorDataDeRetorno");
		List<Cobranca> cobrancas = cobrancaRepository.buscaCobrancas(dataDeRetorno);
		log.info("[finaliza] CobrancaApplicationService - buscaCobrancasPorDataDeRetorno");
		return CobrancaPorDateListResponse.converte(cobrancas);
	}

	@Override
	public CobrancaAndClienteDetalhadoResponse buscaTodasCobrancasDoCliente(UUID idCliente, UUID idBoleto,
			UUID idCobranca) {
		log.info("[inicia] CobrancaApplicationService - buscaTodasCobrancasDoCliente");
		CobrancaDetalhadoResponse cobrancas = buscaCobrancaDoBoletoComId(idBoleto, idCobranca);
		Boleto boleto = boletoRepository.buscaBoletoPeloId(cobrancas.getIdBoleto());
		clienteRepository.buscaClienteAtravesId(idCliente);
		Cliente cliente = clienteRepository.buscaClienteAtravesId(boleto.getIdClienteComercial());
		Cobranca cobranca = cobrancaRepository.buscaCobrancasDoCliente(idCobranca);
		log.info("[finaliza] CobrancaApplicationService - buscaTodasCobrancasDoCliente");
		CobrancaAndClienteDetalhadoResponse cobrancaDetalhada = new CobrancaAndClienteDetalhadoResponse(cobranca,
				boleto, cliente);
		return cobrancaDetalhada;
	}
}