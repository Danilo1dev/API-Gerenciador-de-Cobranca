package br.com.naturaves.cobrancanaturaves.boleto.infra;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import br.com.naturaves.cobrancanaturaves.boleto.application.repository.TituloRepository;
import br.com.naturaves.cobrancanaturaves.boleto.domain.Boleto;
import br.com.naturaves.cobrancanaturaves.handler.APIException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Repository
@RequiredArgsConstructor
@Log4j2
public class TituloInfraRepository implements TituloRepository {

	private final TituloInfraJpaRepository tituloInfraJpaRepository;

	@Override
	public List<Boleto> buscaBoletoVencido(String nomeVendedor) {
		log.info("[start] TituloInfraRepository - buscaBoletoVencido");
		List<Boleto> boleto = tituloInfraJpaRepository.buscaBoletoVencidoPorVendedor(nomeVendedor);
		log.info("[finish] TituloInfraRepository - buscaBoletoVencido");
		return boleto;
	}

	@Override
	public Boleto buscaBoleto(UUID idTitulo) {
		log.info("[start] TituloInfraRepository - buscaBoleto");
		var boleto = tituloInfraJpaRepository.findById(idTitulo)
				.orElseThrow(() -> APIException.build(HttpStatus.BAD_REQUEST, "Título não encontrado"));
		log.info("[fim] TituloInfraRepository - buscaBoleto");
		return boleto;
	}

	@Override
	public Boleto salvar(Boleto boleto) {
		log.info("[start] TituloInfraRepository - salvar");
		boleto = tituloInfraJpaRepository.save(boleto);
		log.info("[start] TituloInfraRepository - salvar");
		return boleto;
	}

}
