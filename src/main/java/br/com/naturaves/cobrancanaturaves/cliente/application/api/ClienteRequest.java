package br.com.naturaves.cobrancanaturaves.cliente.application.api;

import java.time.LocalDate;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import br.com.naturaves.cobrancanaturaves.cliente.domain.InscricaoSocial;
import lombok.Value;

@Value
public class ClienteRequest {
    @NotBlank
    private String cliente;
    @NotBlank
    private String nomeCliente;
    private InscricaoSocial inscricaoSocial;

    private String telefone;
    @Email
    private String email;
    @NotBlank
    private String nomeVendedor;
    @NotNull
    private LocalDate dataDoCadastro;
}
