package br.com.naturaves.cobrancanaturaves.cobranca.application.api;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import br.com.naturaves.cobrancanaturaves.cobranca.domain.Cobranca;
import lombok.Value;

@Value
public class CobrancaResponse {
	private UUID idCobranca;
}
