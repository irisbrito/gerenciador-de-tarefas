package br.com.zup.gerenciador.tarefas.exceptions;

import org.springframework.http.HttpStatus;

public class ErroDoSistema extends RuntimeException {
    private HttpStatus status;
    private String tipoDoErro;

    public ErroDoSistema(HttpStatus status, String tipoDoErro, String mensagem) {
        super(mensagem;);
        this.status= status;
        this.tipoDoErro = tipoDoErro;;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getTipoDoErro() {
        return tipoDoErro;
    }
}
