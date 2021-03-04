package br.com.springbank.controller.form;

import br.com.springbank.modelo.Usuario;
import br.com.springbank.repository.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class UsuarioForm {

    @NotNull
    @NotEmpty
    private String usuario;

    @NotNull
    @NotEmpty
    private String senha;

//    @NotNull
//    @NotEmpty
//    private String nomePerfil;

    public String getusuario() {
        return usuario;
    }

    public void setUsuario(String nome) {
        this.usuario = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

//    public String getNomePerfil() {
//        return nomePerfil;
//    }
//
//    public void setNomePerfil(String nomePerfil) {
//        this.nomePerfil = nomePerfil;
//    }

    public Usuario converter(/*PerfilRepository repository*/) {
        //List<Perfil> perfil = repository.findByNome("ROLE_" + nomePerfil.toUpperCase());
        String senhaCript = new BCryptPasswordEncoder().encode(this.senha);
        return new Usuario(this.usuario,senhaCript/*,perfil*/);
    }

    public Usuario atualizar(Long id, UsuarioRepository usuarioRepository/*,PerfilRepository perfilRepository*/) {
        Usuario usuario = usuarioRepository.getOne(id);
        usuario.setUsuario(this.usuario);
        usuario.setSenha(new BCryptPasswordEncoder().encode(this.senha));
//        List<Perfil> perfil = perfilRepository.findByNome("ROLE_" + nomePerfil.toUpperCase());
//        usuario.setPerfis(perfil);
        return usuario;
    }
}
