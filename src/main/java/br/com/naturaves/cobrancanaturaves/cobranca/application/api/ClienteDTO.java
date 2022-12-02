package br.com.naturaves.cobrancanaturaves.cobranca.application.api;

import br.com.naturaves.cobrancanaturaves.cliente.domain.Cliente;
import lombok.Value;

@Value
public class ClienteDTO {

	private String cliente;
	private String nomeCliente;
	private String telefone;

	public ClienteDTO(Cliente cliente) {
		this.cliente = cliente.getCliente();
		this.nomeCliente = cliente.getNomeCliente();
		this.telefone = cliente.getTelefone();
	}

}
