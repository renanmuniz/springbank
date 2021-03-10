package br.com.springbank.repository;

import br.com.springbank.modelo.Agencia;
import br.com.springbank.modelo.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AgenciaRepository extends JpaRepository<Agencia, Long> {
    Optional<Agencia> findByNumero(Long numero);

    Page<Agencia> findByNumero(Long numero, Pageable paginacao);
}
