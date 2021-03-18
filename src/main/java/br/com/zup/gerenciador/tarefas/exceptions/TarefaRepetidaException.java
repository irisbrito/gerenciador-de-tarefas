package br.com.zup.gerenciador.tarefas.exceptions;

import org.springframework.http.HttpStatus;

public class TarefaRepetidaException extends ErroDoSistema {
    public TarefaRepetidaException() {
        super(HttpStatus.BAD_REQUEST, "tarefa", "Tarefa repetida!");
    }
}
