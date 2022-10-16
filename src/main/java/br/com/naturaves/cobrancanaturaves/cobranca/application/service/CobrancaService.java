package br.com.naturaves.cobrancanaturaves.cobranca.application.service;

import java.util.List;
import java.util.UUID;
import javax.validation.Valid;

import br.com.naturaves.cobrancanaturaves.cobranca.application.api.*;

public interface CobrancaService {
	CobrancaResponse criaCobranca(UUID idBoleto, @Valid CobrancaRequest cobrancaRequest);
	List<CobrancaBoletoListResponse> buscaCobrancaDoBoletoComId(UUID idBoleto);
	CobrancaDetalhadoResponse buscaCobrancaDoBoletoComId(UUID idBoleto, UUID idCobranca);
	void deletaCobrancaDoBoletoComId(UUID idBoleto, UUID idCobranca);
	void alteraCobrancaDoBoletoComId(UUID idBoleto, UUID idCobranca, CobrancaAlteracaoRequest cobrancaAlteracaoRequest);
}
