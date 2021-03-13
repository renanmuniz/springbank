package br.com.springbank.modelo;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Lancamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull
    private Conta conta;

    @NotNull
    private String descricao;

    @NotNull
    private Double valor;

    @NotNull
    private LocalDateTime dataHoraLcto;

    public Lancamento() {
    }

    public Lancamento(@NotNull Conta conta, @NotNull String descricao, @NotNull Double valor, @NotNull LocalDateTime dataHoraLcto) {
        this.conta = conta;
        this.descricao = descricao;
        this.valor = valor;
        this.dataHoraLcto = dataHoraLcto;
    }

    public Long getId() {
        return id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lancamento that = (Lancamento) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (conta != null ? !conta.equals(that.conta) : that.conta != null) return false;
        if (descricao != null ? !descricao.equals(that.descricao) : that.descricao != null) return false;
        if (valor != null ? !valor.equals(that.valor) : that.valor != null) return false;
        return dataHoraLcto != null ? dataHoraLcto.equals(that.dataHoraLcto) : that.dataHoraLcto == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (conta != null ? conta.hashCode() : 0);
        result = 31 * result + (descricao != null ? descricao.hashCode() : 0);
        result = 31 * result + (valor != null ? valor.hashCode() : 0);
        result = 31 * result + (dataHoraLcto != null ? dataHoraLcto.hashCode() : 0);
        return result;
    }
}
