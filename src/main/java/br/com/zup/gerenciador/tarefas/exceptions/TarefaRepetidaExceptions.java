package br.com.zup.gerenciador.tarefas.exceptions;

import org.springframework.http.HttpStatus;

public class TarefaRepetidaExceptions extends ErroDoSistema {
    public TarefaRepetidaExceptions() {
        super(HttpStatus.BAD_REQUEST, "tarefa", "Tarefa repetida!");
    }
}

