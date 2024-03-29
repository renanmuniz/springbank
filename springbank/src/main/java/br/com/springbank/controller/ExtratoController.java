package br.com.springbank.controller;

import br.com.springbank.controller.dto.LancamentoDto;
import br.com.springbank.controller.form.ExtratoForm;
import br.com.springbank.modelo.Conta;
import br.com.springbank.modelo.Lancamento;
import br.com.springbank.repository.ContaRepository;
import br.com.springbank.repository.LancamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


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

            return LancamentoDto.converter(lancamentos);
        } else {
            throw new NoSuchElementException();
        }
    }

}
