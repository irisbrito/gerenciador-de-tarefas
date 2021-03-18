package br.com.zup.gerenciador.tarefas.exceptions;

import org.springframework.http.HttpStatus;

public class UsuarioNaoEncontradoException extends ErroDoSistema{
    public UsuarioNaoEncontradoException() {
        super(HttpStatus.BAD_REQUEST, "usuario", "Usuário não encontrado");
    }
}
