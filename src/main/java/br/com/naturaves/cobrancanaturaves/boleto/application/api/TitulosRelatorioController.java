package br.com.naturaves.cobrancanaturaves.boleto.application.api;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import br.com.naturaves.cobrancanaturaves.boleto.application.service.TituloService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequiredArgsConstructor
public class TitulosRelatorioController implements TitulosRelatorioAPI {

	private final TituloService tituloService;

	@Override
	public List<BoletoClienteListVencidosResponse> getBoletoVencidoPorDataVencimento(String nomeVendedor, LocalDate dataVencimento) {
		log.info("[inicia] TitulosRelatorioController - getBoletoVencidoPorDataVencimento");
		List<BoletoClienteListVencidosResponse> boletoVencido = tituloService.buscaBoletosVencidosPorNome(nomeVendedor, dataVencimento);
		log.info("[finaliza] TitulosRelatorioController - getBoletoVencidoPorDataVencimento");
		return boletoVencido;
	}

}
