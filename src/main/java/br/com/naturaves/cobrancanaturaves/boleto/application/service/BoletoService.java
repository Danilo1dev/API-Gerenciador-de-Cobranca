package br.com.naturaves.cobrancanaturaves.boleto.application.service;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import br.com.naturaves.cobrancanaturaves.boleto.application.api.BoletoAlteracaoRequest;
import br.com.naturaves.cobrancanaturaves.boleto.application.api.BoletoClienteListResponse;
import br.com.naturaves.cobrancanaturaves.boleto.application.api.BoletoDetalhadoResponse;
import br.com.naturaves.cobrancanaturaves.boleto.application.api.BoletoListResponse;
import br.com.naturaves.cobrancanaturaves.boleto.application.api.BoletoRequest;
import br.com.naturaves.cobrancanaturaves.boleto.application.api.BoletoResponse;
import br.com.naturaves.cobrancanaturaves.boleto.domain.Boleto;

public interface BoletoService {
	BoletoResponse criaBoleto(UUID idCliente, @Valid BoletoRequest boletoRequest);
	void criaListaBoletos(String boletoCsvRequest);
	List<BoletoClienteListResponse> buscaBoletoDoClienteComId(UUID idCliente);
	BoletoDetalhadoResponse buscaBoletoDoClienteComId(UUID idCliente, UUID idBoleto);
	void deletaBoletoDoClienteComId(UUID idCliente, UUID idBoleto);
	void alteraBoletoDoClienteComId(UUID idCliente, UUID idBoleto, BoletoAlteracaoRequest boletoAlteracaoRequest);
	Boleto buscaBoletoComIdBoleto(UUID idBoleto);
	List<BoletoClienteListResponse> buscaBoletoVencido(UUID idCliente);
}
