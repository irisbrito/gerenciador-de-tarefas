package br.com.zup.gerenciador.tarefas.Controllers;

import br.com.zup.gerenciador.tarefas.Services.TarefaService;
import br.com.zup.gerenciador.tarefas.models.Tarefa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
}
