package br.com.springbank.controller;

import br.com.springbank.controller.dto.LancamentoDto;
import br.com.springbank.controller.dto.UsuarioDto;
import br.com.springbank.controller.form.LancamentoForm;
import br.com.springbank.modelo.Lancamento;
import br.com.springbank.modelo.Usuario;
import br.com.springbank.repository.ContaRepository;
import br.com.springbank.repository.LancamentoRepository;
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
@RequestMapping("/lancamento")
public class LancamentoController {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private LancamentoRepository lancamentoRepository;

    /**
     *
     * @param numeroConta
     * @return
     */
    @GetMapping
    public Page<LancamentoDto> listar(@RequestParam(required = false) Long numeroConta,
                                      @PageableDefault(sort="id", direction = Sort.Direction.ASC, page = 0, size = 10)
                                           Pageable paginacao) {
        if(numeroConta == null) {
            Page<Lancamento> lancamentos = lancamentoRepository.findAll(paginacao);
            return LancamentoDto.converter(lancamentos);
        } else {
            Page<Lancamento> lancamentos = lancamentoRepository.findByContaNumero(numeroConta,paginacao);
            return LancamentoDto.converter(lancamentos);
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
    public ResponseEntity<LancamentoDto> cadastrar(@RequestBody @Valid LancamentoForm form,
                                                   UriComponentsBuilder uriBuilder) {
        Lancamento lancamento = form.converter(contaRepository);
        lancamento.setDataHoraLcto(LocalDateTime.now());
        lancamentoRepository.save(lancamento);
        URI uri = uriBuilder.path("/lancamento/{id}").buildAndExpand(lancamento.getId()).toUri();
        return ResponseEntity.created(uri).body(new LancamentoDto(lancamento));
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<LancamentoDto> detalhar(@PathVariable Long id) {
        Optional<Lancamento> lancamentoOptional = lancamentoRepository.findById(id);
        if(lancamentoOptional.isPresent()) {
            return ResponseEntity.ok(new LancamentoDto(lancamentoOptional.get()));
        }
        return ResponseEntity.notFound().build();
    }


    /**
     *
     * @param id
     * @param form
     * @return
     */
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<LancamentoDto> atualizar(@PathVariable Long id, @RequestBody @Valid LancamentoForm form) {
        Optional<Lancamento> lancamentoOptional = lancamentoRepository.findById(id);
        if(lancamentoOptional.isPresent()) {
            Lancamento lancamento = form.atualizar(lancamentoRepository, id, form.getValor());
            return ResponseEntity.ok(new LancamentoDto(lancamento));
        }
        return ResponseEntity.notFound().build();
    }

    /**
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id) {
        Optional<Lancamento> lancamentoOptional = lancamentoRepository.findById(id);
        if(lancamentoOptional.isPresent()) {
            lancamentoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
