package br.com.naturaves.cobrancanaturaves.boleto.application.api;

import java.util.UUID;

import org.springframework.web.bind.annotation.RestController;

import br.com.naturaves.cobrancanaturaves.boleto.application.service.TituloService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;


@RestController
@Log4j2
@RequiredArgsConstructor
public class TituloController implements TituloApi {

	private final TituloService tituloService;
	
	@Override
	public void patchTitulo(UUID idTitulo) {
		log.info("[inicio]- TituloController - patchTitulo ");
		tituloService.quitarTitulo(idTitulo);
		log.info("[Fim]- TituloController - patchTitulo ");
	}

}
