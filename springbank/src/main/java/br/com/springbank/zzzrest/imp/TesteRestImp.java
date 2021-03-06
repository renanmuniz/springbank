package br.com.springbank.zzzrest.imp;

import br.com.springbank.zzzhexagono.servicos.repositorio.contrato.RepTeste;
import br.com.springbank.zzzhexagono.servicos.repositorio.dominio.Teste;
import br.com.springbank.zzzrest.contrato.TesteAdaptadorRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * Gerenciador do adptador primário hexagonal endpoint REST de teste.
 * @author Renan Muniz Merlo
 * @version 1.0 - 02/03/21
 * @since 02/03/21
 */

@RestController
@RequestMapping(TesteAdaptadorRest.TESTAR)
public class TesteRestImp implements TesteAdaptadorRest {

    @Autowired
    private RepTeste rep;

    @Override
    @GetMapping
    public void testar(@RequestParam(required = false) String nome) {
        System.out.println("Chamado endpoint de teste " + LocalDateTime.now());
        Teste repTeste = rep.findByNomeTeste(nome);
        System.out.println(repTeste.getNomeTeste());
    }
}
