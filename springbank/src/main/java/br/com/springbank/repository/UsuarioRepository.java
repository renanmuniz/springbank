package br.com.springbank.repository;

import br.com.springbank.modelo.Usuario;
import br.com.springbank.zzzhexagono.servicos.repositorio.dominio.Teste;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByNome(String usuario);

    Page<Usuario> findByNome(String usuario, Pageable paginacao);
}
