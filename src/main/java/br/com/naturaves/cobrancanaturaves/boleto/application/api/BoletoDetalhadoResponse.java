package br.com.naturaves.cobrancanaturaves.boleto.application.api;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
import br.com.naturaves.cobrancanaturaves.boleto.domain.Boleto;
import br.com.naturaves.cobrancanaturaves.boleto.domain.GrupoEmpresarial;
import lombok.Value;

@Value
public class BoletoDetalhadoResponse {
    private UUID idBoleto;
    private String documento;
    private String parcela;
    private LocalDate dataVencimento;
    private BigDecimal saldoDevedor;
    private GrupoEmpresarial grupoEmpresarial;
    private LocalDateTime dataHoraDaCadastro;
    
    public BoletoDetalhadoResponse(Boleto boleto) {
        this.idBoleto = boleto.getIdBoleto();
        this.documento = boleto.getDocumento();
        this.parcela = boleto.getParcela();
        this.dataVencimento = boleto.getDataVencimento();
        this.saldoDevedor = boleto.getSaldoDevedor();
        this.grupoEmpresarial = boleto.getGrupoEmpresarial();
        this.dataHoraDaCadastro = boleto.getDataHoraDaCadastro();
	}
}
