package br.com.naturaves.cobrancanaturaves.cobranca.application.repository;

import java.util.List;
import java.util.UUID;
import br.com.naturaves.cobrancanaturaves.cobranca.domain.Cobranca;

public interface CobrancaRepository {
	Cobranca salvaCobranca(Cobranca cobranca);
	List<Cobranca> salvarCobrancas(List<Cobranca> cobrancas);
	List<Cobranca> buscaCobrancaDoBoletoComId(UUID idBoleto);
	Cobranca buscaCobrancaComId(UUID idCobranca);
	void deletaCobranca(Cobranca cobranca);
}
