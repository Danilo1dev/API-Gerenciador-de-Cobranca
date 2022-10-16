package br.com.naturaves.cobrancanaturaves.boleto.application.service;

import br.com.naturaves.cobrancanaturaves.cobranca.application.api.CobrancaRequest;
import br.com.naturaves.cobrancanaturaves.cobranca.domain.Cobranca;
import br.com.naturaves.cobrancanaturaves.handler.APIException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import br.com.naturaves.cobrancanaturaves.cobranca.application.api.CobrancaRequest;
import br.com.naturaves.cobrancanaturaves.cobranca.domain.Cobranca;
import br.com.naturaves.cobrancanaturaves.handler.APIException;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ExtrairBoletosAtravesCsvApplicationService implements ExtrairBoletosAtravesArquivoService {

    @Override
    public List<Boleto> extrair(String arquivoCodificado) {
        try {
            List<Boleto> boletos = new ArrayList<>();
            String line = "";
            byte[] decodedFile = Base64.getDecoder().decode(arquivoCodificado);

            File tempFile = File.createTempFile("file", ".csv", null);
            FileOutputStream fos = new FileOutputStream(tempFile);
            fos.write(decodedFile);

            BufferedReader br = new BufferedReader(new FileReader(tempFile.getAbsolutePath()));

            while ((line = br.readLine()) != null) {
                String[] dataLine = line.split(",");

                UUID idBoleto = UUID.fromString(dataLine[0]);

                BoletoRequest boletoRequest = new BoletoRequest(
                        dataLine[1],
                        dataLine[2],
                        LocalDate.parse(dataLine[3], DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                        Double.parseDouble(dataLine[4]),
                        GrupoEmpresarial.valueOf(dataLine[5])
                );

                boletos.add(new Boleto(idBoleto, boletoRequest));
            }

            return boletos;
        } catch (IOException e) {
            throw APIException.build(HttpStatus.BAD_REQUEST, "Error durante a leitura do arquivo.");
        }
    }
}
