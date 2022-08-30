package br.com.naturaves.cobrancanaturaves.boleto.apllication.service;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import br.com.naturaves.cobrancanaturaves.boleto.apllication.api.BoletoAlteracaoRequest;
import br.com.naturaves.cobrancanaturaves.boleto.apllication.api.BoletoClienteListResponse;
import br.com.naturaves.cobrancanaturaves.boleto.apllication.api.BoletoDetalhadoResponse;
import br.com.naturaves.cobrancanaturaves.boleto.apllication.api.BoletoRequest;
import br.com.naturaves.cobrancanaturaves.boleto.apllication.api.BoletoResponse;

public interface BoletoService {
	BoletoResponse criaBoleto(UUID idCliente, @Valid BoletoRequest boletoRequest);
	List<BoletoClienteListResponse> buscaBoletoDoClienteComId(UUID idCliente);
	BoletoDetalhadoResponse buscaBoletoDoClienteComId(UUID idCliente, UUID idBoleto);
	void deletaBoletoDoClienteComId(UUID idCliente, UUID idBoleto);
	void alteraBoletoDoClienteComId(UUID idCliente, UUID idBoleto, BoletoAlteracaoRequest boletoAlteracaoRequest);
}
