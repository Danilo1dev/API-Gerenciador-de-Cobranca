package br.com.naturaves.cobrancanaturaves.cobranca.application.service;

import br.com.naturaves.cobrancanaturaves.cobranca.domain.Cobranca;

import java.util.List;

public interface ExtrairCobrancasAtravesArquivoService {
    List<Cobranca> extrair(String arquivoCodificado);
}
