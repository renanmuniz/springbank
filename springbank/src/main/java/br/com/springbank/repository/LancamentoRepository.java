package br.com.springbank.repository;

import br.com.springbank.modelo.Conta;
import br.com.springbank.modelo.Lancamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {

    Page<Lancamento> findByContaNumero(Long numero, Pageable paginacao);

    List<Lancamento> findByDataHoraLctoBetweenAndConta(LocalDateTime data1, LocalDateTime data2, Conta conta);

}
