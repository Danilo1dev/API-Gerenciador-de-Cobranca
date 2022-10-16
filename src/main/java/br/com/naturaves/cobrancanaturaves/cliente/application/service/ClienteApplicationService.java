package br.com.naturaves.cobrancanaturaves.cliente.application.service;

import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import org.springframework.stereotype.Service;
import br.com.naturaves.cobrancanaturaves.cliente.application.api.ClienteAlteracaoRequest;
import br.com.naturaves.cobrancanaturaves.cliente.application.api.ClienteDetalhadoResponse;
import br.com.naturaves.cobrancanaturaves.cliente.application.api.ClienteListResponse;
import br.com.naturaves.cobrancanaturaves.cliente.application.api.ClienteRequest;
import br.com.naturaves.cobrancanaturaves.cliente.application.api.ClienteResponse;
import br.com.naturaves.cobrancanaturaves.cliente.application.repository.ClienteRepository;
import br.com.naturaves.cobrancanaturaves.cliente.domain.Cliente;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class ClienteApplicationService implements ClienteService {
	private final ClienteRepository clienteRepository;

	@Override
	public ClienteResponse criaCliente(ClienteRequest clienteRequest) {
		log.info("[inicia] ClienteApplicationService - criaCliente");
		Cliente cliente = clienteRepository.salva(new Cliente(clienteRequest));
		log.info("[finaliza] ClienteApplicationService - criaCliente");
		return ClienteResponse.builder()
				.idCliente(cliente.getIdCliente())
				.build();
	}

	@Override
	public List<ClienteListResponse> buscaTodosClientes() {
		log.info("[inicia] ClienteApplicationService - buscaTodosClientes");
		List<Cliente> clientes = clienteRepository.buscaTodosClientes();
		log.info("[finaliza] ClienteApplicationService - buscaTodosClientes");
		return ClienteListResponse.converte(clientes);
	}

	@Override
	public ClienteDetalhadoResponse buscaClienteAtravesID(UUID idCliente) {
		log.info("[inicia] ClienteApplicationService - buscaClienteAtravesId");
		Cliente cliente = clienteRepository.buscaClienteAtravesId(idCliente);
		log.info("[finaliza] ClienteApplicationService - buscaClienteAtravesId");
		return new ClienteDetalhadoResponse(cliente);
	}

	@Override
	public void deletaClienteAtravesID(UUID idCliente) {
		log.info("[inicia] ClienteApplicationService - deletaClienteAtravesID");
		Cliente cliente = clienteRepository.buscaClienteAtravesId(idCliente);
		clienteRepository.deletaCliente(cliente);
		log.info("[finaliza] ClienteApplicationService - deletaClienteAtravesID");
	}

	@Override
	public void patchAlteracliente(UUID idCliente, @Valid ClienteAlteracaoRequest clienteAlteracaoRequest) {
		log.info("[inicia] ClienteApplicationService - patchAlteracliente");
		Cliente cliente = clienteRepository.buscaClienteAtravesId(idCliente);
		cliente.altera(clienteAlteracaoRequest);
		clienteRepository.salva(cliente);
		log.info("[finaliza] ClienteApplicationService - patchAlteracliente");
	}
}
