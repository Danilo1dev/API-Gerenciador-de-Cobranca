package br.com.naturaves.cobrancanaturaves.boleto.application.service;

import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import org.springframework.stereotype.Service;
import br.com.naturaves.cobrancanaturaves.boleto.application.api.BoletoAlteracaoRequest;
import br.com.naturaves.cobrancanaturaves.boleto.application.api.BoletoClienteListResponse;
import br.com.naturaves.cobrancanaturaves.boleto.application.api.BoletoDetalhadoResponse;
import br.com.naturaves.cobrancanaturaves.boleto.application.api.BoletoListResponse;
import br.com.naturaves.cobrancanaturaves.boleto.application.api.BoletoRequest;
import br.com.naturaves.cobrancanaturaves.boleto.application.api.BoletoResponse;
import br.com.naturaves.cobrancanaturaves.boleto.application.repository.BoletoRepository;
import br.com.naturaves.cobrancanaturaves.boleto.domain.Boleto;
import br.com.naturaves.cobrancanaturaves.cliente.application.service.ClienteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class BoletoApplicationService implements BoletoService {
    private final ClienteService clienteService;
    private final BoletoRepository boletoRepository;
    private final ExtrairBoletosAtravesArquivoService extrairBoletosAtravesArquivoService;

    @Override
    public BoletoResponse criaBoleto(UUID idCliente, @Valid BoletoRequest boletoRequest) {
        log.info("[inicia] BoletoApplicationService - criaBoleto");
        clienteService.buscaClienteAtravesID(idCliente);
        Boleto boleto = boletoRepository.salvaBoleto(new Boleto(idCliente, boletoRequest));
        log.info("[finaliza] BoletoApplicationService - criaBoleto");
        return new BoletoResponse(boleto.getIdBoleto());
    }

    @Override
    public List<BoletoListResponse> criaListaBoletos(String boletoCsvRequest) {
        log.info("[inicia] BoletoApplicationService - criaListaCobrancas");
        List<Boleto> boletosExtraidosArquivo = extrairBoletosAtravesArquivoService.extrair(boletoCsvRequest);
        List<Boleto> boletosCadastrados = boletoRepository.salvarListaBoletos(boletosExtraidosArquivo);
        log.info("[inicia] BoletoApplicationService - criaListaCobrancas");
        return BoletoListResponse.converte(boletosCadastrados);
    }

    @Override
    public List<BoletoClienteListResponse> buscaBoletoDoClienteComId(UUID idCliente) {
        log.info("[inicia] BoletoApplicationService - buscaBoletoDoClienteComId");
        clienteService.buscaClienteAtravesID(idCliente);
        List<Boleto> boletoDoCliente = boletoRepository.buscaBoletoDoClienteComId(idCliente);
        log.info("[finaliza] BoletoApplicationService - buscaBoletoDoClienteComId");
        return BoletoClienteListResponse.converte(boletoDoCliente);
    }

    @Override
    public BoletoDetalhadoResponse buscaBoletoDoClienteComId(UUID idCliente, UUID idBoleto) {
        log.info("[inicia] BoletoApplicationService - buscaBoletoDoClienteComId");
        clienteService.buscaClienteAtravesID(idCliente);
        Boleto boleto = boletoRepository.buscaBoletoPeloId(idBoleto);
        log.info("[finaliza] BoletoApplicationService - buscaBoletoDoClienteComId");
        return new BoletoDetalhadoResponse(boleto);
    }

    @Override
    public void deletaBoletoDoClienteComId(UUID idCliente, UUID idBoleto) {
        log.info("[inicia] BoletoApplicationService - deletaBoletoDoClienteComId");
        clienteService.buscaClienteAtravesID(idCliente);
        Boleto boleto = boletoRepository.buscaBoletoPeloId(idBoleto);
        boletoRepository.deletaBoletoId(boleto);
        log.info("[finaliza] BoletoApplicationService - deletaBoletoDoClienteComId");
    }

    @Override
    public void alteraBoletoDoClienteComId(UUID idCliente, UUID idBoleto, BoletoAlteracaoRequest boletoAlteracaoRequest) {
        log.info("[inicia] BoletoApplicationService - alteraBoletoDoClienteComId");
        clienteService.buscaClienteAtravesID(idCliente);
        Boleto boleto = boletoRepository.buscaBoletoPeloId(idBoleto);
        boleto.altera(boletoAlteracaoRequest);
        boletoRepository.salvaBoleto(boleto);
        log.info("[finaliza] BoletoApplicationService - alteraBoletoDoClienteComId");
    }

    @Override
    public Boleto buscaBoletoComIdBoleto(UUID idBoleto) {
        Boleto boleto = boletoRepository.buscaBoletoPeloId(idBoleto);
        return boleto;
    }

	@Override
	public List<BoletoClienteListResponse> buscaBoletoVencido(UUID idCliente) {
		 log.info("[inicia] BoletoApplicationService - buscaBoletoVencido");
	        clienteService.buscaClienteAtravesID(idCliente);
	        List<Boleto> boletoVencidoDoCliente = boletoRepository.buscaBoletoVencido(idCliente);
	        log.info("[finaliza] BoletoApplicationService - buscaBoletoVencido");
	        return BoletoClienteListResponse.converte(boletoVencidoDoCliente);
		
	}
}
