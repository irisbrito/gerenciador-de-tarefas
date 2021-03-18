package br.com.zup.gerenciador.tarefas.services;

import br.com.zup.gerenciador.tarefas.exceptions.ListaDeTarefasNaoConcluidasException;
import br.com.zup.gerenciador.tarefas.exceptions.TarefaNaoConcluidaException;
import br.com.zup.gerenciador.tarefas.exceptions.TarefaNaoEncontradaException;
import br.com.zup.gerenciador.tarefas.exceptions.TarefaRepetidaExceptions;
import br.com.zup.gerenciador.tarefas.models.Status;
import br.com.zup.gerenciador.tarefas.models.Tarefa;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TarefaService {

    List<Tarefa> tarefas = new ArrayList<>();

    private void colocarStatusCerto(Tarefa tarefa) {
        LocalDate hoje = LocalDate.now();

        if (hoje.isAfter(tarefa.getPrazo())) {
            tarefa.setStatus(Status.ATRASADO);
        } else {
            tarefa.setStatus(Status.NAO_CONCLUIDO);
        }
    }

    public Tarefa cadastrarTarefa(Tarefa tarefa){
        tarefaRepetida(tarefa.getNome());
        tarefa.setDataEntrada(LocalDate.now());
        colocarStatusCerto(tarefa);
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
        }throw new TarefaNaoEncontradaException();
    }

    private void validaTarefaConcluida(Tarefa tarefa) {
        if (!tarefa.getStatus().equals(Status.CONCLUIDO)) {
            throw new TarefaNaoConcluidaException();
        }
    }

    public void deletarTarefa(String nome) {
        Tarefa tarefa = retornarTarefaPeloNome(nome);

        validaTarefaConcluida(tarefa);

        tarefas.remove(tarefa);
    }

    public Tarefa alterarTarefa(Tarefa tarefa){
       Tarefa tarefaAtualizada = retornarTarefaPeloNome(tarefa.getNome());

       tarefaAtualizada.setNome(tarefa.getNome());
       tarefaAtualizada.setStatus(tarefa.getStatus());
       tarefaAtualizada.setDescricao(tarefa.getDescricao());
       tarefaAtualizada.setPrazo(tarefa.getPrazo());
       tarefaAtualizada.setEmailUsuario(tarefa.getEmailUsuario());
       tarefaAtualizada.setDataEntrada(tarefa.getDataEntrada());

       return tarefaAtualizada;
    }

    public void tarefaRepetida(String nome){
        for (Tarefa tarefa : tarefas) {
            if(tarefa.getNome().equalsIgnoreCase(nome)){
                throw new TarefaRepetidaExceptions();
            }
        }
    }

    public List<List<Tarefa>> pesquisarTarefasDoUsuario(String email){
        List<Tarefa> todasAsTarefas = pesquisarTodasAsTarefasDoUsuario(email);
        List<Tarefa> tarefasNaoConcluidas = new ArrayList<>();
        List<Tarefa> tarefasConcluidas = new ArrayList<>();

        for(Tarefa tarefa : todasAsTarefas){
            if(tarefa.getStatus().equals(Status.NAO_CONCLUIDO)){
                tarefasNaoConcluidas.add(tarefa);

            } else {
                tarefasConcluidas.add(tarefa);
            }

        }

        return Arrays.asList(tarefasNaoConcluidas, tarefasConcluidas);
    }


}
