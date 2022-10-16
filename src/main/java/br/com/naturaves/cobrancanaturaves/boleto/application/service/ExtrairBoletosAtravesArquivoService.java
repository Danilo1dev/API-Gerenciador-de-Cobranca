package br.com.naturaves.cobrancanaturaves.boleto.application.service;

import br.com.naturaves.cobrancanaturaves.boleto.domain.Boleto;
import java.util.List;

public interface ExtrairBoletosAtravesArquivoService {
    List<Boleto> extrair(String arquivoCodificado);
}
