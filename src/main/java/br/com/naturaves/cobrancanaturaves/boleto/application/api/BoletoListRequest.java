package br.com.naturaves.cobrancanaturaves.boleto.application.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoletoListRequest {
    @NotNull
    @NotBlank(message = "Insira o arquivo CSV em Base64")
    private String data;
}
