package br.com.naturaves.cobrancanaturaves.cobranca.application.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import br.com.naturaves.cobrancanaturaves.cobranca.application.api.CobrancaAlteracaoRequest;
import br.com.naturaves.cobrancanaturaves.cobranca.application.api.CobrancaAndClienteDetalhadoResponse;
import br.com.naturaves.cobrancanaturaves.cobranca.application.api.CobrancaBoletoListResponse;
import br.com.naturaves.cobrancanaturaves.cobranca.application.api.CobrancaDetalhadoResponse;
import br.com.naturaves.cobrancanaturaves.cobranca.application.api.CobrancaPorDateListResponse;
import br.com.naturaves.cobrancanaturaves.cobranca.application.api.CobrancaRequest;
import br.com.naturaves.cobrancanaturaves.cobranca.application.api.CobrancaResponse;

public interface CobrancaService {
    CobrancaResponse criaCobranca(UUID idBoleto, @Valid CobrancaRequest cobrancaRequest);
    List<CobrancaBoletoListResponse> buscaCobrancaDoBoletoComId(UUID idBoleto);
    CobrancaDetalhadoResponse buscaCobrancaDoBoletoComId(UUID idBoleto, UUID idCobranca);
    void deletaCobrancaDoBoletoComId(UUID idBoleto, UUID idCobranca);
    void alteraCobrancaDoBoletoComId(UUID idBoleto, UUID idCobranca, CobrancaAlteracaoRequest cobrancaAlteracaoRequest);
    List<CobrancaPorDateListResponse> buscaCobrancasPorDataDeRetorno(LocalDate dataDeRetorno);
	CobrancaAndClienteDetalhadoResponse buscaTodasCobrancasDoCliente(UUID idCliente, UUID idBoleto, UUID idCobranca);

}
