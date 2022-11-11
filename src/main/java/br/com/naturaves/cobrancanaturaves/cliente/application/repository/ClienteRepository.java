package br.com.naturaves.cobrancanaturaves.cliente.application.repository;

import java.util.List;
import java.util.UUID;
import br.com.naturaves.cobrancanaturaves.cliente.domain.Cliente;

public interface ClienteRepository {
	Cliente salva(Cliente cliente);
	List<Cliente> buscaTodosClientes();
	Cliente buscaClienteAtravesId(UUID idCliente);
	void deletaCliente(Cliente cliente);
	Cliente buscaClienteAtravesCliente(String cliente);
}
