package br.com.naturaves.cobrancanaturaves.cobranca.application.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.validation.Valid;

import br.com.naturaves.cobrancanaturaves.cobranca.application.api.*;
import org.springframework.cglib.core.VisibilityPredicate;
import org.springframework.stereotype.Service;
import br.com.naturaves.cobrancanaturaves.boleto.application.service.BoletoService;
import br.com.naturaves.cobrancanaturaves.cobranca.application.repository.CobrancaRepository;
import br.com.naturaves.cobrancanaturaves.cobranca.domain.Cobranca;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class CobrancaApplicationService implements CobrancaService {
    private final BoletoService boletoService;
    private final CobrancaRepository cobrancaRepository;
    
    @Override
    public CobrancaResponse criaCobranca(UUID idBoleto, @Valid CobrancaRequest cobrancaRequest) {
        log.info("[inicia] CobrancaApplicationService - criaCobranca");
        //boletoService.buscaBoletoComIdBoleto(idBoleto);
        Cobranca novaCobranca = new Cobranca(idBoleto, cobrancaRequest);
        Cobranca cobranca = cobrancaRepository.salvaCobranca(novaCobranca);
        log.info("[finaliza] CobrancaApplicationService - criaCobranca");
        return new CobrancaResponse(cobranca.getIdCobranca());
    }

    @Override
    public List<CobrancaBoletoListResponse> buscaCobrancaDoBoletoComId(UUID idBoleto) {
        log.info("[inicia] CobrancaApplicationService - buscaCobrancaDoBoletoComId");
        boletoService.buscaBoletoComIdBoleto(idBoleto);
        List<Cobranca> cobrancaDoBoleto = cobrancaRepository.buscaCobrancaDoBoletoComId(idBoleto); 
        log.info("[finaliza] CobrancaApplicationService - buscaCobrancaDoBoletoComId");
        return CobrancaBoletoListResponse.converte(cobrancaDoBoleto);
    }

    @Override
    public CobrancaDetalhadoResponse buscaCobrancaDoBoletoComId(UUID idBoleto, UUID idCobranca) {
        log.info("[inicia] CobrancaApplicationService - buscaCobrancaDoBoletoComId");
        boletoService.buscaBoletoComIdBoleto(idBoleto);
        Cobranca cobranca = cobrancaRepository.buscaCobrancaComId(idCobranca);
        log.info("[finaliza] CobrancaApplicationService - buscaCobrancaDoBoletoComId");
        return new CobrancaDetalhadoResponse(cobranca);
    }

    @Override
    public void deletaCobrancaDoBoletoComId(UUID idBoleto, UUID idCobranca) {
        log.info("[inicia] CobrancaApplicationService - deletaCobrancaDoBoletoComId");
        boletoService.buscaBoletoComIdBoleto(idBoleto);
        Cobranca cobranca = cobrancaRepository.buscaCobrancaComId(idCobranca);
        cobrancaRepository.deletaCobranca(cobranca);
        log.info("[finaliza] CobrancaApplicationService - deletaCobrancaDoBoletoComId");
    }

    @Override
    public void alteraCobrancaDoBoletoComId(UUID idBoleto, UUID idCobranca, CobrancaAlteracaoRequest cobrancaAlteracaoRequest) {
        log.info("[inicia] CobrancaApplicationService - alteraCobrancaDoBoletoComId");
        boletoService.buscaBoletoComIdBoleto(idBoleto);
        Cobranca cobranca = cobrancaRepository.buscaCobrancaComId(idCobranca);
        cobranca.altera(cobrancaAlteracaoRequest);
        cobrancaRepository.salvaCobranca(cobranca);
        log.info("[finaliza] CobrancaApplicationService - alteraCobrancaDoBoletoComId");
    }

    @Override
    public List<CobrancaPorDateListResponse> buscaCobrancasPorDataDeRetorno(LocalDate dataDeRetorno) {
        log.info("[inicia] CobrancaApplicationService - buscaCobrancasPorDataDeRetorno");
        //boletoService.buscaBoletoComIdBoleto(idBoleto);
        List<Cobranca> cobrancas = cobrancaRepository.buscaCobrancas(dataDeRetorno);
        log.info("[finaliza] CobrancaApplicationService - buscaCobrancasPorDataDeRetorno");
        return CobrancaPorDateListResponse.converte(cobrancas);
    }
}