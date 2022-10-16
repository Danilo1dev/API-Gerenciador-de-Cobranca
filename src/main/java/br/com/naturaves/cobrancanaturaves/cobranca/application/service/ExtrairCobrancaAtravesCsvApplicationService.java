package br.com.naturaves.cobrancanaturaves.cobranca.application.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
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
public class ExtrairCobrancaAtravesCsvApplicationService implements ExtrairCobrancasAtravesArquivoService {
    @Override
    public List<Cobranca> extrair(String arquivoCodificado) {
        try {
            List<Cobranca> cobrancas = new ArrayList<>();
            String line = "";
            byte[] decodedFile = Base64.getDecoder().decode(arquivoCodificado);

            File tempFile = File.createTempFile("file", ".csv", null);
            FileOutputStream fos = new FileOutputStream(tempFile);
            fos.write(decodedFile);

            BufferedReader br = new BufferedReader(new FileReader(tempFile.getAbsolutePath()));

            while ((line = br.readLine()) != null) {
                String[] dataLine = line.split(",");

                UUID idBoleto = UUID.fromString(dataLine[0]);

                CobrancaRequest cobrancaRequest = new CobrancaRequest(
                        Double.parseDouble(dataLine[1]),
                        dataLine[2],
                        LocalDate.parse(dataLine[3], DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                );

                cobrancas.add(new Cobranca(idBoleto, cobrancaRequest));
            }

            return cobrancas;
        } catch (IOException e) {
            throw APIException.build(HttpStatus.BAD_REQUEST, "Error durante a leitura do arquivo.");
        }
    }
}
