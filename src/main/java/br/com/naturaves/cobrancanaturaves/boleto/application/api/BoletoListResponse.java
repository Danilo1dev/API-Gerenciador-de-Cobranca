package br.com.naturaves.cobrancanaturaves.boleto.application.api;

import br.com.naturaves.cobrancanaturaves.boleto.domain.Boleto;
import lombok.Value;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Value
public class BoletoListResponse {
    private UUID idBoleto;

    public BoletoListResponse(Boleto boleto) {
        this.idBoleto = boleto.getIdBoleto();
    }

    public static List<BoletoListResponse> converte(List<Boleto> boletos) {
        return boletos.stream()
                .map(BoletoListResponse::new)
                .collect(Collectors.toList());
    }
}
