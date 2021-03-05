package br.com.springbank.controller;

import br.com.springbank.controller.dto.UsuarioDto;
import br.com.springbank.controller.form.UsuarioForm;
import br.com.springbank.modelo.Usuario;
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
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    /**
     *
     * @param nomeUsuario
     * @return
     */
    @GetMapping
    public Page<UsuarioDto> listar(@RequestParam(required = false) String nomeUsuario,
                                   @PageableDefault(sort="id", direction = Sort.Direction.ASC, page = 0, size = 10)
                                           Pageable paginacao) {
        if(nomeUsuario == null) {
            Page<Usuario> usuarios = usuarioRepository.findAll(paginacao);
            return UsuarioDto.converter(usuarios);
        } else {
            Page<Usuario> usuarios = usuarioRepository.findByNome(nomeUsuario,paginacao);
            return UsuarioDto.converter(usuarios);
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
    public ResponseEntity<UsuarioDto> cadastrar(@RequestBody @Valid UsuarioForm form,
                                                   UriComponentsBuilder uriBuilder) {
        Usuario usuario = form.converter(perfilRepository);
        usuarioRepository.save(usuario);
        usuario.setData_criacao(LocalDateTime.now());
        URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new UsuarioDto(usuario));
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> detalhar(@PathVariable Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if(usuarioOptional.isPresent()) {
            return ResponseEntity.ok(new UsuarioDto(usuarioOptional.get()));
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
    public ResponseEntity<UsuarioDto> atualizar(@PathVariable Long id, @RequestBody @Valid UsuarioForm form) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if(usuarioOptional.isPresent()) {
            Usuario usuario = form.atualizar(id, usuarioRepository, perfilRepository);
            usuario.setData_alteracao(LocalDateTime.now());
            return ResponseEntity.ok(new UsuarioDto(usuario));
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
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if(usuarioOptional.isPresent()) {
            usuarioRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
