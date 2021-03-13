package br.com.springbank.repository;

import br.com.springbank.modelo.Conta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContaRepository extends JpaRepository<Conta, Long> {
    Optional<Conta> findByNumero(Long numero);

    Optional<Conta> findByUsuarioNome(String nome);

    Page<Conta> findByUsuarioNome(String nome, Pageable paginacao);

    Page<Conta> findByNumero(Long numero, Pageable paginacao);
}
