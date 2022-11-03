package br.com.naturaves.cobrancanaturaves.boleto.application.repository;

import java.time.LocalDate;
import java.util.List;

import br.com.naturaves.cobrancanaturaves.boleto.domain.Boleto;

public interface TituloRepository {

	List<Boleto> buscaBoletoVencido(String nomeVendedor, LocalDate dataVencimento);

}
