package br.com.naturaves.cobrancanaturaves.cobranca.application.api;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CobrancaListRequest {
    @NotNull
    @NotBlank(message = "Insira o arquivo Csv em Base64")
    private String data;
}
