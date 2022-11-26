package br.com.naturaves.cobrancanaturaves.cobranca.application.api;

import java.util.List;
import java.util.UUID;

import br.com.naturaves.cobrancanaturaves.cobranca.domain.Cobranca;
import lombok.Getter;

@Getter
public class CobrancaTituloListResponse {

	private UUID idBoleto;
	private Double valorNegociado;
	private String anotacao;

	public static List<CobrancaTituloListResponse> converte(List<Cobranca> cobrancaTitulo) {
		return null;
	}

	public CobrancaTituloListResponse(Cobranca cobranca) {
		this.idBoleto = cobranca.getIdBoleto();
		this.valorNegociado = cobranca.getValorNegociado();
		this.anotacao = cobranca.getAnotacao();
	}

}
