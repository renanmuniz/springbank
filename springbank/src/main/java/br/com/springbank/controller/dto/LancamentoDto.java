package br.com.springbank.controller.dto;

import br.com.springbank.modelo.Conta;
import br.com.springbank.modelo.Lancamento;
import br.com.springbank.modelo.Perfil;
import br.com.springbank.modelo.Usuario;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LancamentoDto {

    private Long id;
    private Conta conta;
    private String descricao;
    private Double valor;
    private LocalDateTime dataHoraLcto;

    public LancamentoDto(Lancamento lancamento) {
        this.id = lancamento.getId();
        this.conta = lancamento.getConta();
        this.descricao = lancamento.getDescricao();
        this.valor = lancamento.getValor();
        this.dataHoraLcto = lancamento.getDataHoraLcto();
    }

    public static Page<LancamentoDto> converter(Page<Lancamento> usuarios) {
        return usuarios.map(LancamentoDto::new);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
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
