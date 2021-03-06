package br.com.springbank.zzzhexagono.servicos.repositorio.contrato;

import br.com.springbank.zzzhexagono.servicos.repositorio.dominio.Teste;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepTeste extends JpaRepository<Teste, String> {
    Teste findByNomeTeste(String nome);
}
