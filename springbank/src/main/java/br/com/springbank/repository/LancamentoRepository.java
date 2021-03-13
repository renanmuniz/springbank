package br.com.springbank.repository;

import br.com.springbank.modelo.Lancamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {

    Page<Lancamento> findByContaNumero(Long numero, Pageable paginacao);

}
