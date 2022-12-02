package br.com.naturaves.cobrancanaturaves.cobranca.application.api;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.naturaves.cobrancanaturaves.boleto.domain.Boleto;
import lombok.Value;

@Value
public class BoletoDTO {

	private String documento;
	private String parcela;
	private LocalDate dataVencimento;
	private BigDecimal saldoDevedor;

	public BoletoDTO(Boleto boleto) {
		this.documento = boleto.getDocumento();
		this.parcela = boleto.getParcela();
		this.dataVencimento = boleto.getDataVencimento();
		this.saldoDevedor = boleto.getSaldoDevedor();
	}

}
