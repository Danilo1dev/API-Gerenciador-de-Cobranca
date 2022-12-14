package br.com.naturaves.cobrancanaturaves.boleto.infra;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import br.com.naturaves.cobrancanaturaves.boleto.application.repository.BoletoRepository;
import br.com.naturaves.cobrancanaturaves.boleto.domain.Boleto;
import br.com.naturaves.cobrancanaturaves.handler.APIException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Repository
@Log4j2
@RequiredArgsConstructor
public class BoletoInfraRepository implements BoletoRepository {
	private final BoletoSprindDataJPARepository boletoSpringDataJPARepository;
	
	
	@Override
	public Boleto salvaBoleto(Boleto boleto) {
		log.info("[inicia] BoletoInfraRepository - salvaBoleto");
		boletoSpringDataJPARepository.save(boleto);
		log.info("[finaliza] BoletoInfraRepository - salvaBoleto");
		return boleto;
	}

	@Override
	@Transactional
	public List<Boleto> salvarListaBoletos(List<Boleto> boletos) {
		try {
			log.info("[inicia] BoletoInfraRepository - salvarListBoletos");
			List<Boleto> boletosCadastrados = boletoSpringDataJPARepository.saveAll(boletos);
			log.info("[finaliza] BoletoInfraRepository - salvarListBoletos");
			return boletosCadastrados;
		} catch (Exception ex) {
			throw APIException.build(HttpStatus.BAD_REQUEST, "Error ao cadastrar os boletos");
		}
	}

	@Override
	public List<Boleto> buscaBoletoDoClienteComId(UUID idCliente) {
		log.info("[inicia] BoletoInfraRepository - buscaBoletoDoClienteComId");
		var boleto = boletoSpringDataJPARepository.findByIdClienteComercial(idCliente);
		log.info("[finaliza] BoletoInfraRepository - buscaBoletoDoClienteComId");
		return boleto;
	}

	@Override
	public Boleto buscaBoletoPeloId(UUID idBoleto) {
		log.info("[inicia] BoletoInfraRepository - buscaBoletoPeloId");
		var boleto = boletoSpringDataJPARepository.findById(idBoleto)
				.orElseThrow(() -> APIException.build(HttpStatus.NOT_FOUND, "Boleto n??o encontrado pelo idBoleto " + idBoleto));
		log.info("[finaliza] BoletoInfraRepository - buscaBoletoPeloId");
		return boleto;
	}

	@Override
	public void deletaBoletoId(Boleto boleto) {
		log.info("[inicia] BoletoInfraRepository - deletaBoletoId");
		boletoSpringDataJPARepository.delete(boleto);
		log.info("[finaliza] BoletoInfraRepository - deletaBoletoId");
	}

	@Override
	public List<Boleto> buscaBoletoVencido(UUID idCliente) {
			log.info("[inicia] BoletoInfraRepository - buscaBoletoVencido");
			List<Boleto> listaDeBoletosVencidos = boletoSpringDataJPARepository.findByIdClienteComercial(idCliente);
			log.info("[finaliza] BoletoInfraRepository - buscaBoletoVencido");
			return listaDeBoletosVencidos;
	}
}
	
