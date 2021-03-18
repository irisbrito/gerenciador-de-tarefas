package br.com.zup.gerenciador.tarefas.exceptions;

import org.springframework.http.HttpStatus;

public class EmailRepetidoException extends ErroDoSistema {
    public EmailRepetidoException() {
        super(HttpStatus.BAD_REQUEST, "usuario", "E-mail do usuário repetido");
    }
}
