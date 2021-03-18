package br.com.zup.gerenciador.tarefas.exceptions;

import br.com.zup.gerenciador.tarefas.models.Tarefa;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

public class ListaDeTarefasNaoConcluidasException extends ErroDoSistema {

    List<Tarefa> tarefasNaoConcluidas = new ArrayList<>();

    public ListaDeTarefasNaoConcluidasException() {
        super(HttpStatus.BAD_REQUEST, "tarefa", "Tarefas não concluídas");
    }

    public List<Tarefa> getTarefasNaoConcluidas() {
        return tarefasNaoConcluidas;
    }

    public void setTarefasNaoConcluidas(List<Tarefa> tarefasNaoConcluidas) {
        this.tarefasNaoConcluidas = tarefasNaoConcluidas;
    }
}
