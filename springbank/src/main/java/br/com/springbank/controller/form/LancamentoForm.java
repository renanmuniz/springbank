package br.com.springbank.controller.form;

import br.com.springbank.modelo.Conta;
import br.com.springbank.modelo.Lancamento;
import br.com.springbank.modelo.Perfil;
import br.com.springbank.modelo.Usuario;
import br.com.springbank.repository.ContaRepository;
import br.com.springbank.repository.LancamentoRepository;
import br.com.springbank.repository.PerfilRepository;
import br.com.springbank.repository.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public class LancamentoForm {

    @NotNull
    @NotEmpty
    private String usuario;

    @NotNull
    @NotEmpty
    private String descricao;

    @NotNull
    private Double valor;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
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

    public Lancamento converter(ContaRepository repository) {
        Conta conta = repository.findByUsuarioNome(this.usuario).get();
        return new Lancamento(conta, descricao, valor, LocalDateTime.now());
    }

    public Lancamento atualizar(LancamentoRepository lancamentoRepository,Long id, Double valor) {
        Lancamento lancamento = lancamentoRepository.getOne(id);
        lancamento.setValor(valor);
        return lancamento;
    }
}
