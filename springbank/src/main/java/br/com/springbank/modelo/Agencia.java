package br.com.springbank.modelo;

import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Entity
public class Agencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true)
    @NotNull
    @NotEmpty
    private Long numero;

    @NotNull
    @NotEmpty
    private LocalDateTime data_criacao;

    private LocalDateTime data_alteracao;

    public Agencia() {
    }

    public Agencia(@NotEmpty Long numero, @NotEmpty LocalDateTime data_criacao) {
        this.numero = numero;
        this.data_criacao = data_criacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Agencia agencia = (Agencia) o;

        if (id != null ? !id.equals(agencia.id) : agencia.id != null) return false;
        if (numero != null ? !numero.equals(agencia.numero) : agencia.numero != null) return false;
        if (data_criacao != null ? !data_criacao.equals(agencia.data_criacao) : agencia.data_criacao != null)
            return false;
        return data_alteracao != null ? data_alteracao.equals(agencia.data_alteracao) : agencia.data_alteracao == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (numero != null ? numero.hashCode() : 0);
        result = 31 * result + (data_criacao != null ? data_criacao.hashCode() : 0);
        result = 31 * result + (data_alteracao != null ? data_alteracao.hashCode() : 0);
        return result;
    }

    public Long getId() {
        return id;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
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
