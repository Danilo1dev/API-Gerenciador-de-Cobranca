package br.com.naturaves.cobrancanaturaves.cobranca.application.api;

import java.util.UUID;

import lombok.Getter;

@Getter
public class CobrancaTituloListResponse {

	private UUID idBoleto;
	private Double valorNegociado;
	private String anotacao;

}
