package br.com.naturaves.cobrancanaturaves.boleto.application.api;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
	    	List<Boleto> boletosFiltrados = boletoVencido.stream().filter(boleto -> {
	    		boolean igualMaiorQueDoisDias = boleto.getDataVencimento().isAfter(boleto.getDataVencimento().plusDays(2))
	    				|| boleto.getDataVencimento().isEqual(boleto.getDataVencimento().plusDays(2));
	    		if (igualMaiorQueDoisDias) return true; 
	    			return false;
	    	})
	    			.collect(Collectors.toList());
	    				
			return boletosFiltrados
					.stream()
					.map(BoletoClienteListVencidosResponse::new)
					.collect(Collectors.toList());
		}

		public BoletoClienteListVencidosResponse(Boleto boleto) {
			this.idBoleto = boleto.getIdBoleto();
			this.documento = boleto.getDocumento();
			this.parcela = boleto.getParcela();
			this.dataVencimento = boleto.getDataVencimento();
			this.saldoDevedor = boleto.getSaldoDevedor();
			this.grupoEmpresarial = boleto.getGrupoEmpresarial();
			this.dataHoraDaCadastro = boleto.getDataHoraDaCadastro();
		}

	    
}
