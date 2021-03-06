package br.com.springbank.controller.dto;

import br.com.springbank.modelo.Lancamento;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LancamentoDto {

    private Long id;
    private Long conta;
    private String descricao;
    private Double valor;
    private LocalDateTime dataHoraLcto;

    public LancamentoDto(Lancamento lancamento) {
        this.id = lancamento.getId();
        this.conta = lancamento.getConta().getNumero();
        this.descricao = lancamento.getDescricao();
        this.valor = lancamento.getValor();
        this.dataHoraLcto = lancamento.getDataHoraLcto();
    }

    public static Page<LancamentoDto> converter(Page<Lancamento> lancamentos) {
        return lancamentos.map(LancamentoDto::new);
    }

    public static List<LancamentoDto> converter(List<Lancamento> lancamentos) {
        List<LancamentoDto> lancamentosDto = new ArrayList<>();
        for (Lancamento lancamento : lancamentos) {
            lancamentosDto.add(new LancamentoDto(lancamento));
        }
        return lancamentosDto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getConta() {
        return conta;
    }

    public void setConta(Long conta) {
        this.conta = conta;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public LocalDateTime getDataHoraLcto() {
        return dataHoraLcto;
    }

    public void setDataHoraLcto(LocalDateTime dataHoraLcto) {
        this.dataHoraLcto = dataHoraLcto;
    }
}
