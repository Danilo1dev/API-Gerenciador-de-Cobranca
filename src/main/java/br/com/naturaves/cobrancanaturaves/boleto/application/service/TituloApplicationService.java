package br.com.naturaves.cobrancanaturaves.boleto.application.service;

import java.util.List;
import java.util.UUID;

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
	public List<BoletoClienteListVencidosResponse> buscaBoletosVencidosPorNome(String nomeVendedor) {
		log.info("[inicia] TituloApplicationService - buscaBoletosVencidosPorNome");
		List<Boleto> boletoVencido = tituloRepository.buscaBoletoVencido(nomeVendedor);
		log.info("[finaliza] TituloApplicationService - buscaBoletosVencidosPorNome");
		return BoletoClienteListVencidosResponse.converte(boletoVencido);
	}

	@Override
	public void quitarTitulo(UUID idTitulo) {
		log.info("[inicia] TituloApplicationService - quitarTitulo");
		var boleto = tituloRepository.buscaBoleto(idTitulo);
		boleto.quitar();
		this.tituloRepository.salvar(boleto);
		log.info("[Fim] TituloApplicationService - quitarTitulo");
	}

}
