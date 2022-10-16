package br.com.naturaves.cobrancanaturaves.cliente.application.api;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import br.com.naturaves.cobrancanaturaves.cliente.domain.Cliente;
import br.com.naturaves.cobrancanaturaves.cliente.domain.InscricaoSocial;
import lombok.Value;

@Value
public class ClienteListResponse {
    private UUID idCliente;
    private String cliente;
    private String nomeCliente;
    private InscricaoSocial inscricaoSocial;
    private String telefone;
    private String email;
    private String nomeVendedor;

	public static List<ClienteListResponse> converte(List<Cliente> clientes) {
		return clientes.stream()
				.map(ClienteListResponse::new)
				.collect(Collectors.toList());
}
	public ClienteListResponse(Cliente cliente) {
	    this.idCliente = cliente.getIdCliente();
        this.cliente = cliente.getCliente();
        this.nomeCliente = cliente.getNomeCliente();
        this.inscricaoSocial = cliente.getInscricaoSocial();
        this.telefone = cliente.getTelefone();
        this.email = cliente.getEmail();
        this.nomeVendedor = cliente.getNomeVendedor();
	}
}
