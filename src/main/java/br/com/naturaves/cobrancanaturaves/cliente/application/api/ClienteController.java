package br.com.naturaves.cobrancanaturaves.cliente.application.api;

import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
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
	public ClienteResponse postCliente(ClienteRequest clienteRequest) {
		log.info("[inicia] ClienteController - postCliente");
		ClienteResponse clienteCriado = clienteService.criaCliente(clienteRequest);
		log.info("[finaliza] ClienteController - postCliente");
		return clienteCriado;
	}

	@Override
	public List<ClienteListResponse> getTodosCliente() {
		log.info("[inicia] ClienteController - getTodosCliente");
		List<ClienteListResponse> clientes = clienteService.buscaTodosClientes();
		log.info("[finaliza] ClienteController - getTodosCliente");
		return clientes;
	}

	@Override
	public ClienteDetalhadoResponse getClienteAtravesId(UUID idCliente) {
		log.info("[inicia] ClienteController - getClienteAtravesID");
		log.info("[idCliente] {}", idCliente);
		ClienteDetalhadoResponse clienteDetalhado = clienteService.buscaClienteAtravesID(idCliente);
		log.info("[finaliza] ClienteController - getClienteAtravesID");		
		return clienteDetalhado;
	
	}

	@Override
	public void deletaclienteAtravesId(UUID idCliente) {
		log.info("[inicia] ClienteController - deletaclienteAtravesId");
		log.info("[idCliente] {}", idCliente);
		clienteService.deletaClienteAtravesID(idCliente);
		log.info("[finaliza] ClienteController - deletaclienteAtravesId");				
	}

	@Override
	public void patchAlteraCliente(UUID idCliente, @Valid ClienteAlteracaoRequest clienteAlteracaoRequest) {
		log.info("[inicia] ClienteController - patchAlteracliente");
		log.info("[idCliente] {}", idCliente);
		clienteService.patchAlteracliente(idCliente, clienteAlteracaoRequest);
		log.info("[finaliza] ClienteController - patchAlteracliente");				
	}
}
