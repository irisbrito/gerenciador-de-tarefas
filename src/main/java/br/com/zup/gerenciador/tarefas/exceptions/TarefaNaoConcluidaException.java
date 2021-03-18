package br.com.zup.gerenciador.tarefas.exceptions;

import org.springframework.http.HttpStatus;

public class TarefaNaoConcluidaException extends ErroDoSistema {
    public TarefaNaoConcluidaException() {
        super(HttpStatus.BAD_REQUEST, "tarefa", "Tarefa não foi concluída");
    }
}
