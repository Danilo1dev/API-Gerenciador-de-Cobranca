package br.com.naturaves.cobrancanaturaves.cobranca.application.api;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import br.com.naturaves.cobrancanaturaves.cobranca.domain.Cobranca;
import lombok.Value;

@Value
public class CobrancaDetalhadoResponse {

	private UUID idBoleto;
	private UUID idCobranca;
	private Double valorNegociado;
	private String anotacao;
	private LocalDate dataDeRetorno;

	public CobrancaDetalhadoResponse(Cobranca cobranca) {
		this.idBoleto = cobranca.getIdBoleto();
		this.idCobranca = cobranca.getIdCobranca();
		this.valorNegociado = cobranca.getValorNegociado();
		this.anotacao = cobranca.getAnotacao();
		this.dataDeRetorno = cobranca.getDataDeRetorno();
	}
}
