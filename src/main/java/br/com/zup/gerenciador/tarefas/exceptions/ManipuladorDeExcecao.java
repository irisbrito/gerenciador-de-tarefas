package br.com.zup.gerenciador.tarefas.exceptions;

import br.com.zup.gerenciador.tarefas.dtos.ErroDeValidacaoDTO;
import br.com.zup.gerenciador.tarefas.dtos.RespostaDeErroDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestControllerAdvice
public class ManipuladorDeExcecao extends ResponseEntityExceptionHandler {
    /**
     * Cria a lista contendo as DTOs dos  erros de validação
     *
     * @param excecao a exceção contendo a descrição dos erros
     * @return lista de erros de validação
     */
    private List <ErroDeValidacaoDTO> criarListaDeErrosDeValidacao(MethodArgumentNotValidException excecao) {
        List <ErroDeValidacaoDTO>erros = new ArrayList <>();

        for (FieldError erro: excecao.getFieldErrors()) {
            erros.add(new ErroDeValidacaoDTO(erro.getField(), erro.getDefaultMessage()));
        }

        return erros;
    }

    /**
     * Trata um erro de validação no sistema
     *
     * @param excecao exceção contendo os erros de validação
     * @param headers os cabeçalhos da requisição
     * @param status  o código de status da requisição
     * @param request a requisição em si
     * @return resposta de erro com os erros formatados
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException excecao, HttpHeaders headers, HttpStatus status, WebRequest request) {
        RespostaDeErroDTO resposta = new RespostaDeErroDTO(status, "validacao", criarListaDeErrosDeValidacao(excecao));

        return ResponseEntity.status(status).body(resposta);
    }

    /**
     * Trata os erros gerados pelo nosso sistema
     *
     * @param excecao o erro gerado pelo sistema
     * @return resposta de erro formatada
     */
    @ExceptionHandler({ErroDoSistema.class})
    public ResponseEntity lidaComErrosDoSistema(ErroDoSistema excecao) {
        RespostaDeErroDTO resposta = new RespostaDeErroDTO(excecao.getStatus(), excecao.getTipoDoErro(), excecao.getMessage());

        return ResponseEntity.status(resposta.getStatus()).body(resposta);
    }

    /**
     * Trata erros gerais do sistema
     *
     * @param excecao o erro que aconteceu
     * @return resposta formatada
     */
    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity lidaComRuntimeException(RuntimeException excecao) {
        RespostaDeErroDTO resposta = new RespostaDeErroDTO(HttpStatus.BAD_REQUEST, "geral", excecao.getMessage());

        return ResponseEntity.status(resposta.getStatus()).body(resposta);
    }

}
