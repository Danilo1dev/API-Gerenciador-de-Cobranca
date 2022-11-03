package br.com.naturaves.cobrancanaturaves.boleto.application.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.naturaves.cobrancanaturaves.boleto.application.api.BoletoClienteListVencidosResponse;
import br.com.naturaves.cobrancanaturaves.boleto.application.repository.TituloRepository;
import br.com.naturaves.cobrancanaturaves.boleto.domain.Boleto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class TituloApplicationService implements TituloService {

	private final TituloRepository tituloRepository;

	@Override
	public List<BoletoClienteListVencidosResponse> buscaBoletosVencidosPorNome(String nomeVendedor, LocalDate dataVencimento) {
		log.info("[inicia] TituloApplicationService - buscaBoletosVencidosPorNome");
		List<Boleto> boletoVencido = tituloRepository.buscaBoletoVencido(nomeVendedor, dataVencimento);
		log.info("[finaliza] TituloApplicationService - buscaBoletosVencidosPorNome");
		return BoletoClienteListVencidosResponse.converte(boletoVencido);
	}

}
