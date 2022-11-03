package br.com.naturaves.cobrancanaturaves.boleto.application.api;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import br.com.naturaves.cobrancanaturaves.boleto.domain.Boleto;
import br.com.naturaves.cobrancanaturaves.boleto.domain.GrupoEmpresarial;
import lombok.Value;

@Value
public class BoletoClienteListVencidosResponse {
	
		private UUID idBoleto;
	    private String documento;
	    private String parcela;
	    private LocalDate dataVencimento;
	    private BigDecimal saldoDevedor;
	    private GrupoEmpresarial grupoEmpresarial;
	    private LocalDateTime dataHoraDaCadastro;
	
	    public static List<BoletoClienteListVencidosResponse> converte(List<Boleto> boletoVencido) {
			return null;
		}

}
