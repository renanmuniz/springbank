package br.com.springbank.controller;

import br.com.springbank.controller.dto.ContaDto;
import br.com.springbank.controller.dto.UsuarioDto;
import br.com.springbank.controller.form.ContaForm;
import br.com.springbank.controller.form.UsuarioForm;
import br.com.springbank.modelo.Conta;
import br.com.springbank.modelo.Usuario;
import br.com.springbank.repository.AgenciaRepository;
import br.com.springbank.repository.ContaRepository;
import br.com.springbank.repository.PerfilRepository;
import br.com.springbank.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.Optional;


@RestController
@RequestMapping("/conta")
public class ContaController {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private AgenciaRepository agenciaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     *
     * @param nomeUsuario
     * @return
     */
    @GetMapping
    public Page<ContaDto> listar(@RequestParam(required = false) String nomeUsuario,
                                 @PageableDefault(sort="id", direction = Sort.Direction.ASC, page = 0, size = 10)
                                           Pageable paginacao) {
        if(nomeUsuario == null) {
            Page<Conta> contas = contaRepository.findAll(paginacao);
            return ContaDto.converter(contas);
        } else {
            Page<Conta> contas = contaRepository.findByUsuarioNome(nomeUsuario,paginacao);
            if(contas != null) {
                return ContaDto.converter(contas);
            }
            return null;
        }
    }

    /**
     *
     * @param form
     * @param uriBuilder
     * @return
     */
    @PostMapping
    @Transactional
    public ResponseEntity<ContaDto> cadastrar(@RequestBody @Valid ContaForm form,
                                                   UriComponentsBuilder uriBuilder) {
        Conta conta = form.converter(agenciaRepository, usuarioRepository);
        conta.setData_criacao(LocalDateTime.now());
        contaRepository.save(conta);
        URI uri = uriBuilder.path("/conta/{id}").buildAndExpand(conta.getId()).toUri();
        return ResponseEntity.created(uri).body(new ContaDto(conta));
    }

    /**
     *
     * @param
     * @return
     */
    @GetMapping("/{numero}")
    public ResponseEntity<ContaDto> detalhar(@PathVariable Long numero) {
        Optional<Conta> contaOptional = contaRepository.findByNumero(numero);
        if(contaOptional.isPresent()) {
            return ResponseEntity.ok(new ContaDto(contaOptional.get()));
        }
        return ResponseEntity.notFound().build();
    }


    /**
     *
     * @param numero
     * @param form
     * @return
     */
    @PutMapping("/{numero}")
    @Transactional
    public ResponseEntity<ContaDto> atualizar(@PathVariable Long numero, @RequestBody @Valid ContaForm form) {
        Optional<Conta> contaOptional = contaRepository.findByNumero(numero);
        if(contaOptional.isPresent()) {
            Conta conta = form.atualizar(numero, contaRepository, agenciaRepository ,usuarioRepository);
            conta.setData_alteracao(LocalDateTime.now());
            return ResponseEntity.ok(new ContaDto(conta));
        }
        return ResponseEntity.notFound().build();
    }

    /**
     *
     * @param numero
     * @return
     */
    @DeleteMapping("/{numero}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long numero) {
        Optional<Conta> contaOptional = contaRepository.findByNumero(numero);
        if(contaOptional.isPresent()) {
            contaRepository.deleteById(contaOptional.get().getId());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
