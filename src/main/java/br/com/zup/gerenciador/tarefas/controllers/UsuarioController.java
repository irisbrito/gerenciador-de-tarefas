package br.com.zup.gerenciador.tarefas.controllers;

import br.com.zup.gerenciador.tarefas.dtos.ListaDeTarefasDTO;
import br.com.zup.gerenciador.tarefas.dtos.UsuarioETarefasDTO;
import br.com.zup.gerenciador.tarefas.models.Tarefa;
import br.com.zup.gerenciador.tarefas.models.Usuario;
import br.com.zup.gerenciador.tarefas.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("usuarios/")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario adicionarUsuario(@Valid @RequestBody Usuario usuario) {
        return usuarioService.adicionarUsuario(usuario);
    }

    @GetMapping
    public List <Usuario> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

    @GetMapping("{email}")
    public UsuarioETarefasDTO pesquisarUsuario(@PathVariable String email) {
        Usuario usuario = usuarioService.pesquisarUsuarioPeloEmail(email);
        List <List <Tarefa>> tarefas = usuarioService.pegarTarefasDoUsuario(email);
        ListaDeTarefasDTO listaDeTarefasDTO = new ListaDeTarefasDTO(tarefas);
        return new UsuarioETarefasDTO(usuario, listaDeTarefasDTO);
    }

    @DeleteMapping("{email}/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarUsuarioPeloEmail(@PathVariable String email){
        usuarioService.deletarUsuarioPeloEmail(email);
    }
}
