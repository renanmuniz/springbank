package br.com.springbank.controller;

import br.com.springbank.controller.dto.LancamentoDto;
import br.com.springbank.controller.form.ExtratoForm;
import br.com.springbank.controller.form.LancamentoForm;
import br.com.springbank.modelo.Conta;
import br.com.springbank.modelo.Lancamento;
import br.com.springbank.repository.ContaRepository;
import br.com.springbank.repository.LancamentoRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.io.*;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/extrato")
public class ExtratoController {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private LancamentoRepository lancamentoRepository;

    /**
     *
     * @param form
     * @return
     */
    @GetMapping
    public List<LancamentoDto> listar(@RequestBody @Valid ExtratoForm form) {
        Optional<Conta> conta = contaRepository.findByNumero(form.getConta());
        if (conta.isPresent()) {
            LocalDateTime dataInicial = form.getDataInicial().atTime(00,00,00,000000000);
            LocalDateTime dataFinal = form.getDataFinal().atTime(23,59,59,999999999);
            List<Lancamento> lancamentos = lancamentoRepository.findByDataHoraLctoBetweenAndConta(dataInicial,
                    dataFinal, conta.get());
//            List<Lancamento> lancamentosFiltradorConta = lancamentos.stream()
//                    .filter(l -> l.getConta().getNumero().equals(form.getConta())).collect(Collectors.toList());
//            return LancamentoDto.converter(lancamentosFiltradorConta);

            // TODO retirar daqui e fazer endpoint só para isso.
            try {
                ExtratoPdf extrato = new ExtratoPdf();
                extrato.gerarExtratoPdf(lancamentos);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            return LancamentoDto.converter(lancamentos);
        } else {
            throw new NoSuchElementException();
        }
    }

}
