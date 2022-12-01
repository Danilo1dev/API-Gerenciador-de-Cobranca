package br.com.naturaves.cobrancanaturaves.cobranca.application.api;

import br.com.naturaves.cobrancanaturaves.cobranca.domain.Cobranca;
import lombok.Value;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
@Value
public class CobrancaPorDateListResponse {
    private UUID idBoleto;
    private UUID idCobranca;
    private Double valorNegociado;
    private String anotacao;
    private LocalDate dataDeRetorno;


    public static List<CobrancaPorDateListResponse> converte(List<Cobranca> cobrancas) {
        return cobrancas.stream()
                .map(CobrancaPorDateListResponse::new)
                .collect(Collectors.toList());
    }
    public CobrancaPorDateListResponse(Cobranca cobranca) {
        this.idBoleto = cobranca.getIdBoleto();
        this.idCobranca = cobranca.getIdCobranca();
        this.valorNegociado = cobranca.getValorNegociado();
        this.anotacao = cobranca.getAnotacao();
        this.dataDeRetorno = cobranca.getDataDeRetorno();
    }
}