package br.com.naturaves.cobrancanaturaves.boleto.application.repository;

import java.util.List;
import java.util.UUID;

import br.com.naturaves.cobrancanaturaves.boleto.domain.Boleto;

public interface TituloRepository {

	List<Boleto> buscaBoletoVencido(String nomeVendedor);

	Boleto buscaBoleto(UUID idTitulo);
	
	Boleto salvar(Boleto boleto);

}
