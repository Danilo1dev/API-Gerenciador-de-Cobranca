package br.com.naturaves.cobrancanaturaves.boleto.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import br.com.naturaves.cobrancanaturaves.boleto.application.api.BoletoAlteracaoRequest;
import br.com.naturaves.cobrancanaturaves.boleto.application.api.BoletoRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Entity
public class Boleto {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(columnDefinition = "uuid", name = "idBoleto", unique = true, nullable = false)
	private UUID idBoleto;
	@NotNull
	@Column(columnDefinition = "uuid", name = "idClienteComercial", nullable = false)
	private UUID idClienteComercial;
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
    @Enumerated(EnumType.STRING)
    private GrupoEmpresarial grupoEmpresarial;

    private LocalDateTime dataHoraDaCadastro;
    private LocalDateTime dataHoraDoUltimaAlteracao;
    
    public Boleto(UUID idCliente, @Valid BoletoRequest boletoRequest) {
        this.idClienteComercial = idCliente;
        this.documento = boletoRequest.getDocumento();
        this.parcela = boletoRequest.getParcela();
        this.dataVencimento = boletoRequest.getDataVencimento();
        this.saldoDevedor = boletoRequest.getSaldoDevedor();
        this.grupoEmpresarial = boletoRequest.getGrupoEmpresarial();
        this.dataHoraDaCadastro = LocalDateTime.now();
    }

    public void altera(BoletoAlteracaoRequest boletoRequest) {
        this.documento = boletoRequest.getDocumento();
        this.parcela = boletoRequest.getParcela();
        this.dataVencimento = boletoRequest.getDataVencimento();
        this.saldoDevedor = boletoRequest.getSaldoDevedor();
        this.grupoEmpresarial = boletoRequest.getGrupoEmpresarial();
        this.dataHoraDoUltimaAlteracao = LocalDateTime.now();
    }
}