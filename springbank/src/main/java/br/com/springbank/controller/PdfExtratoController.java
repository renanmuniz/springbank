package br.com.springbank.controller;

import br.com.springbank.controller.dto.ExtratoPdfDto;
import br.com.springbank.controller.form.ExtratoForm;
import br.com.springbank.modelo.Conta;
import br.com.springbank.modelo.Lancamento;
import br.com.springbank.repository.ContaRepository;
import br.com.springbank.repository.LancamentoRepository;
import br.com.springbank.utils.ExtratoPdf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@RestController
@RequestMapping("/extrato/pdf")
public class PdfExtratoController {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private LancamentoRepository lancamentoRepository;

    /**
     *
     * @param form
     * @return
     */
    @PostMapping
    public ResponseEntity<ExtratoPdfDto> gerar(@RequestBody @Valid ExtratoForm form) {
        Optional<Conta> conta = contaRepository.findByNumero(form.getConta());
        if (conta.isPresent()) {
            LocalDateTime dataInicial = form.getDataInicial().atTime(00,00,00,000000000);
            LocalDateTime dataFinal = form.getDataFinal().atTime(23,59,59,999999999);
            List<Lancamento> lancamentos = lancamentoRepository.findByDataHoraLctoBetweenAndConta(dataInicial,
                    dataFinal, conta.get());

            try {
                ExtratoPdf extrato = new ExtratoPdf();
                extrato.gerarExtratoPdf(lancamentos);

                var filename = "recursos/relatoriosJasper/gerados/" + "Extrato" + lancamentos
                        .get(0)
                        .getConta()
                        .getNumero()
                        .toString() + ".pdf";

                var file = new File(filename);
                var path = Paths.get(file.getAbsolutePath());
                var resource = new ByteArrayResource(Files.readAllBytes(path));
                byte[] pdf = Files.readAllBytes(path);
                return ResponseEntity.ok(ExtratoPdfDto.converter(form.getConta(), form.getDataInicial(), form.getDataFinal(), pdf));
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.notFound().build();
            }
        } else {
            throw new NoSuchElementException();
        }
    }

}
