package br.com.naturaves.cobrancanaturaves.boleto.application.api;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/titulos/{idCliente}/boleto")
public interface TitulosRelatorioAPI {

	@GetMapping(value = "/vencimento")
	@ResponseStatus(code = HttpStatus.OK)
	List<BoletoClienteListVencidosResponse> getBoletoVencidoPorDataVencimento(@RequestParam(value = "nomeVendedor")String nomeVendedor,@RequestParam ("dataVencimento") LocalDate dataVencimento);
}
