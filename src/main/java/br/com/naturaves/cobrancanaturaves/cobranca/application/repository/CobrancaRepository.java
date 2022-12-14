package br.com.naturaves.cobrancanaturaves.cobranca.application.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import br.com.naturaves.cobrancanaturaves.cobranca.domain.Cobranca;

public interface CobrancaRepository {
	Cobranca salvaCobranca(Cobranca cobranca);

	List<Cobranca> buscaCobrancaDoBoletoComId(UUID idBoleto);

	Cobranca buscaCobrancaComId(UUID idCobranca);

	void deletaCobranca(Cobranca cobranca);

	List<Cobranca> buscaCobrancas(LocalDate dataDeRetorno);

	Cobranca buscaCobrancasDoCliente(UUID idCobranca);
}
