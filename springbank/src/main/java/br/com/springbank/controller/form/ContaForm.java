package br.com.springbank.controller.form;

import br.com.springbank.modelo.Agencia;
import br.com.springbank.modelo.Conta;
import br.com.springbank.modelo.Perfil;
import br.com.springbank.modelo.Usuario;
import br.com.springbank.repository.AgenciaRepository;
import br.com.springbank.repository.ContaRepository;
import br.com.springbank.repository.PerfilRepository;
import br.com.springbank.repository.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.ValidationException;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;


public class ContaForm {

    @NotNull
    private Integer tipoConta;

    @NotNull
    private Long numero;

    @NotNull
    private Long agencia;

    @NotNull
    @NotEmpty
    private String usuario;

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

    public Conta converter(AgenciaRepository agenciaRepository, UsuarioRepository usuarioRepository) {
        Optional<Agencia> agencia = agenciaRepository.findByNumero(this.agencia);
        Optional<Usuario> usuario = usuarioRepository.findByNome(this.usuario);
        if(agencia.isPresent() && usuario.isPresent()) {
            return new Conta(this.tipoConta, this.numero, agencia.get(), usuario.get());
        }
        throw new ValidationException("Agência ou Usuário Inválido");
    }

    public Conta atualizar(Long numero, ContaRepository contaRepository, AgenciaRepository agenciaRepository, UsuarioRepository usuarioRepository) {
        Conta conta = contaRepository.findByNumero(numero).get();
        conta.setTipoConta(this.tipoConta);
        conta.setNumero(this.numero);
        conta.setAgencia(agenciaRepository.findByNumero(this.agencia).get());
        conta.setUsuario(usuarioRepository.findByNome(this.usuario).get());
        return conta;
    }
}
