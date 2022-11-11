package br.com.naturaves.cobrancanaturaves.boleto.application.service;

import java.util.List;

import br.com.naturaves.cobrancanaturaves.boleto.application.api.BoletoClienteListVencidosResponse;

public interface TituloService {

	List<BoletoClienteListVencidosResponse> buscaBoletosVencidosPorNome(String nomeVendedor);
	
	

}
