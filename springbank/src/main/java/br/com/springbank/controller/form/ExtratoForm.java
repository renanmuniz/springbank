package br.com.springbank.controller.form;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;


public class ExtratoForm {

    @NotNull
    private Long conta;

    private LocalDate dataInicial;

    private LocalDate dataFinal;

    public Long getConta() {
        return conta;
    }

    public void setConta(Long conta) {
        this.conta = conta;
    }

    public LocalDate getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(LocalDate dataInicial) {
        this.dataInicial = dataInicial;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(LocalDate dataFinal) {
        this.dataFinal = dataFinal;
    }
}
