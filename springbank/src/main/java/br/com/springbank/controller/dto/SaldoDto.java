package br.com.springbank.controller.dto;

import br.com.springbank.modelo.Lancamento;

import java.text.DecimalFormat;
import java.util.List;

public class SaldoDto {

    private String saldo;

    public SaldoDto(String saldo) {
        this.saldo = saldo;
    }

    public String getSaldo() {
        return saldo;
    }

    public static Double getSaldo(List<Lancamento> lancamentos) {
        Double saldo = 0.0;
        for (Lancamento lancamento : lancamentos) {
            saldo = saldo + lancamento.getValor();
        }
        return saldo;
    }

    public static SaldoDto converter(Double saldo) {
        DecimalFormat decimal = new DecimalFormat("###,###,###,##0.00");
        return new SaldoDto("R$ " + decimal.format(saldo));
    }
}
