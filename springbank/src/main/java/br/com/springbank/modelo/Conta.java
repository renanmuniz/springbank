package br.com.springbank.modelo;

import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Entity
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Integer tipoConta;

    @Column(unique=true)
    @NotNull
    private Long numero;

    @NotNull
    @ManyToOne
    private Agencia agencia;

    @NotNull
    @ManyToOne
    private Usuario usuario;

    @NotNull
    private LocalDateTime data_criacao;

    private LocalDateTime data_alteracao;

    public Conta() {
    }

    public Conta(@NotEmpty Integer tipoConta, @NotEmpty Long numero, @NotEmpty Agencia agencia, @NotEmpty Usuario usuario) {
        this.tipoConta = tipoConta;
        this.numero = numero;
        this.agencia = agencia;
        this.usuario = usuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Conta conta = (Conta) o;

        if (id != null ? !id.equals(conta.id) : conta.id != null) return false;
        if (tipoConta != null ? !tipoConta.equals(conta.tipoConta) : conta.tipoConta != null) return false;
        if (numero != null ? !numero.equals(conta.numero) : conta.numero != null) return false;
        if (agencia != null ? !agencia.equals(conta.agencia) : conta.agencia != null) return false;
        if (usuario != null ? !usuario.equals(conta.usuario) : conta.usuario != null) return false;
        if (data_criacao != null ? !data_criacao.equals(conta.data_criacao) : conta.data_criacao != null) return false;
        return data_alteracao != null ? data_alteracao.equals(conta.data_alteracao) : conta.data_alteracao == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (tipoConta != null ? tipoConta.hashCode() : 0);
        result = 31 * result + (numero != null ? numero.hashCode() : 0);
        result = 31 * result + (agencia != null ? agencia.hashCode() : 0);
        result = 31 * result + (usuario != null ? usuario.hashCode() : 0);
        result = 31 * result + (data_criacao != null ? data_criacao.hashCode() : 0);
        result = 31 * result + (data_alteracao != null ? data_alteracao.hashCode() : 0);
        return result;
    }

    public Long getId() {
        return id;
    }

    public Integer getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(Integer tipoConta) {
        this.tipoConta = tipoConta;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public Agencia getAgencia() {
        return agencia;
    }

    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getData_criacao() {
        return data_criacao;
    }

    public void setData_criacao(LocalDateTime data_criacao) {
        this.data_criacao = data_criacao;
    }

    public LocalDateTime getData_alteracao() {
        return data_alteracao;
    }

    public void setData_alteracao(LocalDateTime data_alteracao) {
        this.data_alteracao = data_alteracao;
    }
}
