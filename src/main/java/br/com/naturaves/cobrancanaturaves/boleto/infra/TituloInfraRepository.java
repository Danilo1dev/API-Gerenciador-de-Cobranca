package br.com.naturaves.cobrancanaturaves.boleto.infra;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.naturaves.cobrancanaturaves.boleto.application.repository.TituloRepository;
import br.com.naturaves.cobrancanaturaves.boleto.domain.Boleto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Repository
@RequiredArgsConstructor
@Log4j2
public class TituloInfraRepository implements TituloRepository {

	
	
	private final TituloInfraJpaRepository tituloInfraJpaRepository;

	@Override
	public List<Boleto> buscaBoletoVencido(String nomeVendedor, LocalDate dataVencimento) {
		log.info("[start] TituloInfraRepository - buscaBoletoVencido");
		List<Boleto> boleto = tituloInfraJpaRepository.buscaBoletoVencidoPorVendedor(nomeVendedor, dataVencimento);
		log.info("[finish] TituloInfraRepository - buscaBoletoVencido");
		return boleto;
	}

}
