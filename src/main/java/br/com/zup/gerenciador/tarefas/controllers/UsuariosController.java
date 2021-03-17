package br.com.zup.gerenciador.tarefas.controllers;

import br.com.zup.gerenciador.tarefas.models.Usuario;
import br.com.zup.gerenciador.tarefas.services.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("usuarios/")
public class UsuariosController {
    @Autowired
    private UsuariosService usuariosService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario adicionarUsuario(@Valid @RequestBody Usuario usuario) {
        return usuariosService.adicionarUsuario(usuario);
    }

    @GetMapping
    public List <Usuario> listarUsuarios() {
        return usuariosService.listarUsuarios();
    }
}
