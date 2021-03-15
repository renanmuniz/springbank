package br.com.springbank.controller;

import br.com.springbank.controller.dto.LancamentoDto;
import br.com.springbank.controller.dto.SaldoDto;
import br.com.springbank.controller.form.LancamentoForm;
import br.com.springbank.modelo.Conta;
import br.com.springbank.modelo.Lancamento;
import br.com.springbank.repository.ContaRepository;
import br.com.springbank.repository.LancamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@RestController
@RequestMapping("/saldo")
public class SaldoController {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private LancamentoRepository lancamentoRepository;

    /**
     *
     * @param numeroConta
     * @return
     */
    @GetMapping
    public SaldoDto listar(@RequestParam(required = true) Long numeroConta) {
        if(numeroConta == null) {
            throw new IllegalArgumentException("Favor fornecer número da conta para obter saldo.");
        } else {
            Optional<Conta> conta = contaRepository.findByNumero(numeroConta);
            if (conta.isPresent()) {
                LocalDateTime dataInicial = LocalDateTime.of(1,01,01,00,00,00);
                LocalDateTime dataFinal = LocalDateTime.now();
                List<Lancamento> lancamentos = lancamentoRepository.findByDataHoraLctoBetweenAndConta(dataInicial,
                        dataFinal, conta.get());
                return SaldoDto.converter(SaldoDto.getSaldo(lancamentos));
            } else {
                throw new NoSuchElementException();
            }
        }
    }

}
