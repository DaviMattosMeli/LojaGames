package com.lojagames.controller;

import com.lojagames.model.Usuario;
import com.lojagames.model.UsuarioLogin;
import com.lojagames.repository.CategoriaRepository;
import com.lojagames.repository.UsuarioRepository;
import com.lojagames.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/cadastrar")
    public ResponseEntity<Usuario> postUsuario(@Valid @RequestBody Usuario usuario){
        return  usuarioService.cadastrarUsuario(usuario)
                .map(res -> ResponseEntity.status(HttpStatus.CREATED).body(res))
                .orElse(ResponseEntity.badRequest().build());

    }

    @PutMapping("/atualizar")
    public ResponseEntity<Usuario> putUsuario(@Valid @RequestBody Usuario usuario){
        return usuarioService.atualizarUsuario(usuario)
                .map(res -> ResponseEntity.status(HttpStatus.OK).body(res))
                .orElse(ResponseEntity.badRequest().build());

    }

    @PostMapping("/logar")
    public ResponseEntity<UsuarioLogin> autenticarUsuario(@RequestBody Optional<UsuarioLogin> usuarioLogin){
        return usuarioService.autenticarUsuario(usuarioLogin)
                .map(res -> ResponseEntity.status(HttpStatus.OK).body(res))
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    @GetMapping("/listartodos")
    public ResponseEntity<List<Usuario>> getAll(){
        return ResponseEntity.ok(usuarioRepository.findAll());
    }

    @GetMapping("buscarporid/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable Long id) {
        return usuarioRepository.findById(id)
                .map(resposta -> ResponseEntity.ok(resposta))
                .orElse(ResponseEntity.notFound().build());
    }

}
