package br.com.zup.gerenciador.tarefas.dtos;

import br.com.zup.gerenciador.tarefas.models.Tarefa;

import java.util.ArrayList;
import java.util.List;

public class ListaDeTarefasDTO {

    List<Tarefa> tarefasConcluidas = new ArrayList<>();
    List<Tarefa> tarefasNaoConcluidas = new ArrayList();

    public ListaDeTarefasDTO() {
    }

    public ListaDeTarefasDTO(List<Tarefa> tarefasConcluidas, List<Tarefa> tarefasNaoConcluidas) {
        this.tarefasConcluidas = tarefasConcluidas;
        this.tarefasNaoConcluidas = tarefasNaoConcluidas;
    }

    public List<Tarefa> getTarefasConcluidas() {
        return tarefasConcluidas;
    }

    public void setTarefasConcluidas(List<Tarefa> tarefasConcluidas) {
        this.tarefasConcluidas = tarefasConcluidas;
    }

    public List<Tarefa> getTarefasNaoConcluidas() {
        return tarefasNaoConcluidas;
    }

    public void setTarefasNaoConcluidas(List<Tarefa> tarefasNaoConcluidas) {
        this.tarefasNaoConcluidas = tarefasNaoConcluidas;
    }

    public ListaDeTarefasDTO converterDTO(List<Tarefa> tarefasConcluidas, List<Tarefa> tarefasNaoConcluidas){
        ListaDeTarefasDTO listaDeTarefasDTO =  new ListaDeTarefasDTO(tarefasConcluidas, tarefasNaoConcluidas);
        return listaDeTarefasDTO;
    }
}
