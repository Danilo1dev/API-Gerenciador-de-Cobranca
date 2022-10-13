package br.com.naturaves.cobrancanaturaves.cobranca.application.api;

import br.com.naturaves.cobrancanaturaves.cobranca.domain.Cobranca;
import lombok.Value;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Value
public class CobrancaListResponse {
    private UUID idCobranca;

    public CobrancaListResponse(Cobranca cobranca) {
        this.idCobranca = cobranca.getIdCobranca();
    }

    public static List<CobrancaListResponse> converte(List<Cobranca> cobrancas) {
        return cobrancas.stream()
                .map(CobrancaListResponse::new)
                .collect(Collectors.toList());
    }
}
