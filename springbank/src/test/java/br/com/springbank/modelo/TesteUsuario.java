package br.com.springbank.modelo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TesteUsuario {

    public static Usuario fabricarValido() {
        List<Perfil> perfis = new ArrayList<>();
        perfis.add(new Perfil("PerfilTeste"));
        Usuario usuario = new Usuario("Nome teste", "senha teste", perfis);
        return usuario;
}

    @Test
    public void deveValidarCamposObrigatorios() {
    }

}
