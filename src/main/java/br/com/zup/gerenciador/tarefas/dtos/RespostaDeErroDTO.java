package br.com.zup.gerenciador.tarefas.dtos;

import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

public class RespostaDeErroDTO {
    private HttpStatus status;
    private String tipoDoErro;
    private List <ErroDeValidacaoDTO> erros;

    public RespostaDeErroDTO() {
    }

    public RespostaDeErroDTO(HttpStatus status, String tipoDoErro, List<ErroDeValidacaoDTO> erros) {
        this.status = status;
        this.tipoDoErro = tipoDoErro;
        this.erros = erros;
    }

    public RespostaDeErroDTO(HttpStatus status, String tipoDoErro, String mensagem) {
        this.status = status;
        this.tipoDoErro = tipoDoErro;
        erros = Arrays.asList(new ErroDeValidacaoDTO(null, mensagem));
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getTipoDoErro() {
        return tipoDoErro;
    }

    public void setTipoDoErro(String tipoDoErro) {
        this.tipoDoErro = tipoDoErro;
    }

    public List<ErroDeValidacaoDTO> getErros() {
        return erros;
    }

    public void setErros(List<ErroDeValidacaoDTO> erros) {
        this.erros = erros;
    }
}