package br.com.springbank.controller.dto;

import org.springframework.core.io.ByteArrayResource;

import java.time.LocalDate;
import java.util.Arrays;

public class ExtratoPdfDto {

    private Long conta;

    private LocalDate dataInicial;

    private LocalDate dataFinal;

    private String pdfByteArray;

    public ExtratoPdfDto(Long conta, LocalDate dataInicial, LocalDate dataFinal, String pdfByteArray) {
        this.conta = conta;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        this.pdfByteArray = pdfByteArray;
    }

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

    public String getPdfByteArray() {
        return pdfByteArray;
    }

    public void setPdfByteArray(String pdfByteArray) {
        this.pdfByteArray = pdfByteArray;
    }

    public static ExtratoPdfDto converter(Long conta, LocalDate dataInicial, LocalDate dataFinal, byte[] pdf) {
        return new ExtratoPdfDto(conta, dataInicial, dataFinal, Arrays.toString(pdf));
    }
}
