package br.com.naturaves.cobrancanaturaves.boleto.application.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;

import br.com.naturaves.cobrancanaturaves.boleto.domain.GrupoEmpresarial;
import br.com.naturaves.cobrancanaturaves.handler.APIException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import br.com.naturaves.cobrancanaturaves.boleto.application.api.BoletoAlteracaoRequest;
import br.com.naturaves.cobrancanaturaves.boleto.application.api.BoletoClienteListResponse;
import br.com.naturaves.cobrancanaturaves.boleto.application.api.BoletoDetalhadoResponse;
import br.com.naturaves.cobrancanaturaves.boleto.application.api.BoletoRequest;
import br.com.naturaves.cobrancanaturaves.boleto.application.api.BoletoResponse;
import br.com.naturaves.cobrancanaturaves.boleto.application.repository.BoletoRepository;
import br.com.naturaves.cobrancanaturaves.boleto.domain.Boleto;
import br.com.naturaves.cobrancanaturaves.cliente.application.service.ClienteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Log4j2
@RequiredArgsConstructor
public class BoletoApplicationService implements BoletoService {
    private final ClienteService clienteService;
    private final BoletoRepository boletoRepository;

    @Override
    public BoletoResponse criaBoleto(UUID idCliente, @Valid BoletoRequest boletoRequest) {
        log.info("[inicia] BoletoApplicationService - criaBoleto");
        clienteService.buscaClienteAtravesID(idCliente);
        Boleto boleto = boletoRepository.salvaBoleto(new Boleto(idCliente, boletoRequest));
        log.info("[finaliza] BoletoApplicationService - criaBoleto");
        return new BoletoResponse(boleto.getIdBoleto());
    }

    @Override
    public List<BoletoClienteListResponse> buscaBoletoDoClienteComId(UUID idCliente) {
        log.info("[inicia] BoletoApplicationService - buscaBoletoDoClienteComId");
        clienteService.buscaClienteAtravesID(idCliente);
        List<Boleto> boletoDoCliente = boletoRepository.buscaBoletoDoClienteComId(idCliente);
        log.info("[finaliza] BoletoApplicationService - buscaBoletoDoClienteComId");
        return BoletoClienteListResponse.converte(boletoDoCliente);
    }

    @Override
    public BoletoDetalhadoResponse buscaBoletoDoClienteComId(UUID idCliente, UUID idBoleto) {
        log.info("[inicia] BoletoApplicationService - buscaBoletoDoClienteComId");
        clienteService.buscaClienteAtravesID(idCliente);
        Boleto boleto = boletoRepository.buscaBoletoPeloId(idBoleto);
        log.info("[finaliza] BoletoApplicationService - buscaBoletoDoClienteComId");
        return new BoletoDetalhadoResponse(boleto);
    }

    @Override
    public void deletaBoletoDoClienteComId(UUID idCliente, UUID idBoleto) {
        log.info("[inicia] BoletoApplicationService - deletaBoletoDoClienteComId");
        clienteService.buscaClienteAtravesID(idCliente);
        Boleto boleto = boletoRepository.buscaBoletoPeloId(idBoleto);
        boletoRepository.deletaBoletoId(boleto);
        log.info("[finaliza] BoletoApplicationService - deletaBoletoDoClienteComId");
    }

    @Override
    public void alteraBoletoDoClienteComId(UUID idCliente, UUID idBoleto, BoletoAlteracaoRequest boletoAlteracaoRequest) {
        log.info("[inicia] BoletoApplicationService - alteraBoletoDoClienteComId");
        clienteService.buscaClienteAtravesID(idCliente);
        Boleto boleto = boletoRepository.buscaBoletoPeloId(idBoleto);
        boleto.altera(boletoAlteracaoRequest);
        boletoRepository.salvaBoleto(boleto);
        log.info("[finaliza] BoletoApplicationService - alteraBoletoDoClienteComId");
    }

    @Override
    public Boleto buscaBoletoComIdBoleto(UUID idBoleto) {
        Boleto boleto = boletoRepository.buscaBoletoPeloId(idBoleto);
        return boleto;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<BoletoResponse> criaBoletoAtravesCsv(MultipartFile arquivoCsv) {
        log.info("[inicia] BoletoApplicationService - criaBoletoAtravesCsv");
        List<Boleto> boletos = extrairBoletosAtravesCsv(arquivoCsv);
        //boletoRepository.salvarBoletos(boletos);
        return null;
    }

    private List<Boleto> extrairBoletosAtravesCsv(MultipartFile arquivoCsv) {
        List<Boleto> boletos = new ArrayList<>();

        try {
            String linha = "";
            Path tempDir = Files.createTempDirectory("");
            File tempFile = tempDir.resolve(arquivoCsv.getOriginalFilename()).toFile();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(tempFile.getAbsolutePath()));

            while ((linha = bufferedReader.readLine()) != null) {
                String[] dadosDaLinha = linha.split(",");

                UUID idCliente = UUID.fromString(dadosDaLinha[0]);

                BoletoRequest boletoRequest = new BoletoRequest(
                        dadosDaLinha[1],
                        dadosDaLinha[2],
                        LocalDate.parse(dadosDaLinha[3], DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                        Double.parseDouble(dadosDaLinha[4]),
                        GrupoEmpresarial.valueOf(dadosDaLinha[5])
                );

                boletos.add(new Boleto(idCliente, boletoRequest));
            }

            return boletos;
        } catch (IOException e) {
            throw APIException.build(HttpStatus.BAD_REQUEST, "Error durante a leitura do arquivo.");
            //e.printStackTrace();
        }
    }
}
