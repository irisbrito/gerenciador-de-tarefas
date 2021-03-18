package br.com.zup.gerenciador.tarefas.exceptions;

public class EmailRepetidoException extends RuntimeException{

    public EmailRepetidoException(String message) {
        super(message);
    }
}
