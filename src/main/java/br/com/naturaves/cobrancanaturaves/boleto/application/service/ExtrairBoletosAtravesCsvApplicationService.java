package br.com.naturaves.cobrancanaturaves.boleto.application.service;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import br.com.naturaves.cobrancanaturaves.cliente.application.api.ClienteDetalhadoResponse;
import br.com.naturaves.cobrancanaturaves.cliente.application.api.ClienteRequest;
import br.com.naturaves.cobrancanaturaves.cliente.application.api.ClienteResponse;
import br.com.naturaves.cobrancanaturaves.cliente.application.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import br.com.naturaves.cobrancanaturaves.boleto.application.api.BoletoRequest;
import br.com.naturaves.cobrancanaturaves.boleto.domain.Boleto;
import br.com.naturaves.cobrancanaturaves.boleto.domain.GrupoEmpresarial;
import br.com.naturaves.cobrancanaturaves.handler.APIException;
import lombok.extern.log4j.Log4j2;

import javax.transaction.Transactional;

@Service
@Log4j2
@RequiredArgsConstructor
public class ExtrairBoletosAtravesCsvApplicationService implements ExtrairBoletosAtravesArquivoService {
    private final ClienteService clienteService;

    @Override
    @Transactional
    public List<Boleto> extrair(String arquivoCodificado) {
        try {
            log.info("[inicia] ExtrairBoletosAtravesCsvApplicationService - extrair");
            byte[] decodedFile = Base64.getDecoder().decode(arquivoCodificado);

            File tempFile = File.createTempFile("file", ".csv", null);
            FileOutputStream fos = new FileOutputStream(tempFile);
            fos.write(decodedFile);

            BufferedReader br = new BufferedReader(new FileReader(tempFile.getAbsolutePath()));

            List<Boleto> boletosDaPlanilha = this.lerPlanilhaCsv(br);

            log.info("[finaliza] ExtrairBoletosAtravesCsvApplicationService - extrair");

            return boletosDaPlanilha;
        } catch (Exception e) {
            throw APIException.build(HttpStatus.BAD_REQUEST, "Error durante a leitura do arquivo.");
        }
    }

    private List<Boleto> lerPlanilhaCsv(BufferedReader br) {
        try {
            String line;
            List<Boleto> boletos = new ArrayList<>();
            GrupoEmpresarial grupoEmpresarial = null;

            while ((line = br.readLine()) != null) {
                String[] dataLine = line.split(",");

                if (dataLine.length > 1) {
                    GrupoEmpresarial hasEmpresarialGroup = this.extrairGrupoEmpresarial(dataLine[1]);
                    boolean hasEmptySpace = Arrays.stream(dataLine).anyMatch(""::contains);
                    boolean hasInvalidTexts =
                            dataLine[0].contains("Emp") ||
                                    dataLine[0].contains("Empresa") ||
                                    hasEmpresarialGroup != null;

                    if (hasEmpresarialGroup != null) {
                        grupoEmpresarial = hasEmpresarialGroup;
                    }

                    if (dataLine.length >= 15 && !hasEmptySpace && !hasInvalidTexts) {
                        UUID idCliente = this.extrairIdClienteDaLinha(dataLine);
                        BoletoRequest boletoRequest = this.extrairBoletoRequestDaLinha(dataLine, grupoEmpresarial);

                        if (boletoRequest != null) {
                            boletos.add(new Boleto(idCliente, boletoRequest));
                        }
                    }
                }
            }

            return boletos;
        } catch (Exception e) {
            throw APIException.build(HttpStatus.BAD_REQUEST, "Error durante a leitura do arquivo.");
        }
    }

    private GrupoEmpresarial extrairGrupoEmpresarial(String grupoEmpresarial) {
        String grupoEmpresarialMaiusculo = grupoEmpresarial.toLowerCase();

        if (grupoEmpresarialMaiusculo.contains("cesconetto")) {
            return GrupoEmpresarial.CESCONETTO;
        } else if (grupoEmpresarialMaiusculo.contains("naturaves")) {
            return GrupoEmpresarial.OVOS_NATURAVES;
        } else if (grupoEmpresarialMaiusculo.contains("forte")) {
            return GrupoEmpresarial.CRESCE_FORTE;
        } else if (grupoEmpresarialMaiusculo.contains("agroavicula")) {
            return GrupoEmpresarial.AGROAVICULA;
        } else {
            return null;
        }
    }

    private BoletoRequest extrairBoletoRequestDaLinha(String[] dataLine, GrupoEmpresarial grupoEmpresarial) {
        String blo = "BLO";
        String dup = "DUP";

        if (dataLine[5].toUpperCase(Locale.ROOT).contains(dup)) {
            return null;
        }

        String document = dataLine[5].replace(blo + " ", "");
        String installments = dataLine[5].substring(dataLine[5].length() - 1);
        LocalDate dueDate = DateTimeFormatter.ofPattern("dd/MM/yy").parse(dataLine[10], LocalDate::from);
        BigDecimal debitBalance =
                BigDecimal.valueOf(
                        Double.parseDouble(
                                dataLine[12].replace("\"", "").replace(",", ".")
                        )
                ).setScale(2, RoundingMode.HALF_UP);

        return new BoletoRequest(document, installments, dueDate, debitBalance, grupoEmpresarial);
    }

    private UUID extrairIdClienteDaLinha(String[] dataLine) {
        String client = dataLine[7];
        String clientName = dataLine[8];
        String sellerName = dataLine[4];
        LocalDate registrationDate = LocalDate.now();

        try {
            ClienteDetalhadoResponse clienteResponse = clienteService.buscaClienteAtravesCliente(client);
            return clienteResponse.getIdCliente();
        } catch (APIException e) {
            ClienteRequest clienteRequest = new ClienteRequest(client, clientName, null, null, null, sellerName, registrationDate);
            ClienteResponse clienteResponse = clienteService.criaCliente(clienteRequest);
            return clienteResponse.getIdCliente();
        }
    }
}
