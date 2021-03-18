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

    /**
     * Coloca o status da tarefa baseando na data
     *
     * @param tarefa a tarefa que vai ser alterada
     */
    private void colocarStatusCerto(Tarefa tarefa) {
        LocalDate hoje = LocalDate.now();

        if (hoje.isAfter(tarefa.getPrazo())) {
            tarefa.setStatus(Status.ATRASADO);
        } else {
            tarefa.setStatus(Status.NAO_CONCLUIDO);
        }
    }

    /**
     *Cadastra uma tarefa no sistema
     *
     * @param tarefa a tarefa a ser cadastrada
     * @return tarefa
     */
    public Tarefa cadastrarTarefa(Tarefa tarefa){
        tarefaRepetida(tarefa.getNome());
        tarefa.setDataEntrada(LocalDate.now());
        colocarStatusCerto(tarefa);
        tarefas.add(tarefa);
        return tarefa;
    }

    /**
     * Lista todas as tarefas cadastradas no sistema
     *
     * @return lista de tarefas
     */
    public List<Tarefa> listarTarefa(){
        return tarefas;
    }

    /**
     * Retorna a tarefa com o nome passado como parâmetro
     *
     * @param nome string com o nome da tarefa
     * @return tarefa
     */
    public Tarefa retornarTarefaPeloNome(String nome){
        for (Tarefa tarefa : tarefas) {
            if(tarefa.getNome().equalsIgnoreCase(nome)){
                return tarefa;
            }
        }throw new TarefaNaoEncontradaException();
    }

    /**
     * Joga uma exceção se a tarefa não estiver concluída
     *
     * @param tarefa a tarefa a validar
     */
    private void validaTarefaConcluida(Tarefa tarefa) {
        if (!tarefa.getStatus().equals(Status.CONCLUIDO)) {
            throw new TarefaNaoConcluidaException();
        }
    }

    /**
     * Deleta uma tarefa pelo nome
     *
     * @param nome string com o nome da tarefa
     */
    public void deletarTarefa(String nome) {
        Tarefa tarefa = retornarTarefaPeloNome(nome);

        validaTarefaConcluida(tarefa);

        tarefas.remove(tarefa);
    }

    /**
     * Altera uma tarefa
     *
     * @param tarefa os dados que serão alterados
     * @return tarefa atualizada
     */
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

    /**
     * Valida se a tarefa está repetida ou não no sistema
     *
     * @param nome string com o nome da tarefa
     */
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

    public List<Tarefa> pesquisarTodasAsTarefasDoUsuario (String email){

        List<Tarefa> todasAsTarefas = new ArrayList<>();

        for(Tarefa tarefa : tarefas){
            if(tarefa.getEmailUsuario().equalsIgnoreCase(email)) {
                todasAsTarefas.add(tarefa);
            }
        }
            return todasAsTarefas;
    }

}
