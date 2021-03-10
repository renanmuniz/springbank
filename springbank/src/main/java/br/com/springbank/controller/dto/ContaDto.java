package br.com.springbank.controller.dto;

import br.com.springbank.modelo.Conta;
import br.com.springbank.modelo.Perfil;
import br.com.springbank.modelo.Usuario;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class ContaDto {

    private Long numero;
    private Integer tipo;
    private Long agencia;
    private String usuario;

    public ContaDto(Conta conta) {
        this.numero = conta.getNumero();
        this.tipo = conta.getTipoConta();
        this.agencia = conta.getAgencia().getNumero();
        this.usuario = conta.getUsuario().getNome();
    }

    public static Page<ContaDto> converter(Page<Conta> contas) {
        return contas.map(ContaDto::new);
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public Long getAgencia() {
        return agencia;
    }

    public void setAgencia(Long agencia) {
        this.agencia = agencia;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
