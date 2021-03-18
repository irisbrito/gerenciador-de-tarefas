package br.com.zup.gerenciador.tarefas.exceptions;

import org.springframework.http.HttpStatus;

public class TarefaNaoEncontradaException extends ErroDoSistema {
    public TarefaNaoEncontradaException() {
        super(HttpStatus.BAD_REQUEST, "tarefa", "Tarefa não encontrada");
    }
}
