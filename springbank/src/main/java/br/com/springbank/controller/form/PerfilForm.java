package br.com.springbank.controller.form;

import br.com.springbank.modelo.Perfil;
import br.com.springbank.repository.PerfilRepository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Locale;

public class PerfilForm {

    @NotNull
    @NotEmpty
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Perfil converter() {
        return new Perfil(rolePerfil(nome));
    }

    public Perfil atualizar(Long id, PerfilRepository repository) {
        Perfil perfil = repository.getOne(id);
        perfil.setNome(rolePerfil(this.nome));

        return perfil;
    }

    public String rolePerfil(String nome) {
        if(!nome.contains("ROLE_")) {
            return "ROLE_" + nome.toUpperCase(Locale.ROOT);
        } else {
            return nome.toUpperCase(Locale.ROOT);
        }
    }
}
