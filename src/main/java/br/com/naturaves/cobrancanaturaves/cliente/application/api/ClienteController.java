package br.com.naturaves.cobrancanaturaves.cliente.application.api;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import br.com.naturaves.cobrancanaturaves.cliente.application.service.ClienteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequiredArgsConstructor
public class ClienteController implements ClienteAPI {
	private final ClienteService clienteService;

	@Override
	public ClienteResponse postCliente(clienteRequest clienteRequest) {
		log.info("[inicia] ClienteController - postCliente");
		ClienteResponse clienteCriado = clienteService.criaCliente(clienteRequest);
		log.info("[finaliza] ClienteController - postCliente");
		return clienteCriado;
	}

	@Override
	public List<ClienteListResponse> getTodosCliente() {
		log.info("[inicia] ClienteController - getTodosCliente");
		//List<ClienteListResponse> clientes = clienteService.buscaTodosClientes();
		log.info("[finaliza] ClienteController - getTodosCliente");
		//return clientes;
		return null;
	}
}
