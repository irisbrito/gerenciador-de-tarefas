package br.com.zup.gerenciador.tarefas.services;

import br.com.zup.gerenciador.tarefas.models.Status;
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

    private void validaTarefaConcluida(Tarefa tarefa) {
        if (!tarefa.getStatus().equals(Status.CONCLUIDO)) {
            throw new RuntimeException("Tarefa não está concluída");
        }
    }

    public void deletarTarefa(String nome) {
        Tarefa tarefa = retornarTarefaPeloNome(nome);

        validaTarefaConcluida(tarefa);

        tarefas.remove(tarefa);
    }
}
