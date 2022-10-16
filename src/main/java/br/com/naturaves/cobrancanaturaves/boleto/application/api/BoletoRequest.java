package br.com.naturaves.cobrancanaturaves.boleto.application.api;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import br.com.naturaves.cobrancanaturaves.boleto.domain.GrupoEmpresarial;
import lombok.Value;

@Value
public class BoletoRequest {
    @NotNull
    @NotBlank(message = "O numero do boleto não pode estar em branco")
    private String documento;
    @NotNull
    @Size(max = 2)
    private String parcela;
    @NotNull
    private LocalDate dataVencimento;
    @NotNull
    private BigDecimal saldoDevedor;
    private GrupoEmpresarial grupoEmpresarial;
}
