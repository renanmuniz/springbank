package br.com.springbank.zzzhexagono.servicos.repositorio.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="teste")
public class Teste implements Serializable {
    @Id
    @Column(name="nome")
    private String nomeTeste;

    public String getNomeTeste() {
        return nomeTeste;
    }

    public void setNomeTeste(String nomeTeste) {
        this.nomeTeste = nomeTeste;
    }
}
