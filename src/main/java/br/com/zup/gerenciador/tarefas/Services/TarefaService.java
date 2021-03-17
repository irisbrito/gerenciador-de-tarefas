package br.com.zup.gerenciador.tarefas.Services;

import br.com.zup.gerenciador.tarefas.models.Tarefa;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TarefaService {

    List<Tarefa> tarefas = new ArrayList<>();

    public Tarefa cadastrarTarefa(Tarefa tarefa){
        tarefa.setDataEntrada(LocalDate.now());
        tarefas.add(tarefa);
        return tarefa;
    }

    public List<Tarefa> listarTarefa(){
        return tarefas;
    }

    public Tarefa retornarTarefaPeloNome(String nome){
        for (Tarefa tarefa : tarefas) {
            if(tarefa.getNome().equalsIgnoreCase(nome)){
                return tarefa;
            }
        }throw new RuntimeException("Tarefa não encontrada!");
    }

}
