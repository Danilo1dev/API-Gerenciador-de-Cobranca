package br.com.naturaves.cobrancanaturaves.cobranca.application.api;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.naturaves.cobrancanaturaves.boleto.domain.Boleto;
import br.com.naturaves.cobrancanaturaves.cliente.domain.Cliente;
import br.com.naturaves.cobrancanaturaves.cobranca.domain.Cobranca;
import lombok.Value;

@Value
public class CobrancaAndClienteDetalhadoResponse {

	private Double valorNegociado;
	private String anotacao;
	private LocalDate dataDeRetorno;
	private String cliente;
	private String nomeCliente;
	private String telefone;
	private String documento;
	private String parcela;
	private LocalDate dataVencimento;
	private BigDecimal saldoDevedor;

	public CobrancaAndClienteDetalhadoResponse(Cobranca cobranca, Boleto boleto, Cliente cliente) {

		this.valorNegociado = cobranca.getValorNegociado();
		this.anotacao = cobranca.getAnotacao();
		this.dataDeRetorno = cobranca.getDataDeRetorno();
		this.cliente = cliente.getCliente();
		this.nomeCliente = cliente.getNomeCliente();
		this.telefone = cliente.getTelefone();
		this.documento = boleto.getDocumento();
		this.parcela = boleto.getParcela();
		this.dataVencimento = boleto.getDataVencimento();
		this.saldoDevedor = boleto.getSaldoDevedor();
	}

}
