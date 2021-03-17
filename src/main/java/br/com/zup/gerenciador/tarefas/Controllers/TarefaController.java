package br.com.zup.gerenciador.tarefas.controllers;

import br.com.zup.gerenciador.tarefas.services.TarefaService;
import br.com.zup.gerenciador.tarefas.models.Tarefa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tarefas/")
public class TarefaController {

    @Autowired
    TarefaService tarefaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Tarefa cadastrarTarefa (@RequestBody Tarefa tarefa){
        return tarefaService.cadastrarTarefa(tarefa);
    }

    @GetMapping
    public List<Tarefa> listarTarefa(){
        return tarefaService.listarTarefa();
    }

    @GetMapping("{nome}/")
    public Tarefa retornarTarefaPeloNome(@PathVariable String nome){
        return tarefaService.retornarTarefaPeloNome(nome);
    }

    @DeleteMapping("{nome}/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarTarefa(@PathVariable String nome) {
        tarefaService.deletarTarefa(nome);
    }
}
