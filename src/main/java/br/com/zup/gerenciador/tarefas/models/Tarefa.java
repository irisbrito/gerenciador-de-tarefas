package br.com.zup.gerenciador.tarefas.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class Tarefa {
    @NotEmpty(message = "Nome não pode ficar vazio!")
    private String nome;

    @NotEmpty(message = "Descrição não pode ficar vazio!")
    private String descricao;

    private LocalDate dataEntrada;

    @NotEmpty(message = "Prazo não pode ficar vazio!")
    private LocalDate prazo;

    @Email(message = "Email inválido!")
    @NotEmpty(message = "Email não pode ficar vazio!")
    private String emailUsuario;

    @NotNull(message = "Status não pode ser nulo!")
    private Status status;

    public Tarefa() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDate dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public LocalDate getPrazo() {
        return prazo;
    }

    public void setPrazo(LocalDate prazo) {
        this.prazo = prazo;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}