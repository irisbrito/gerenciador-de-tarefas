package br.com.zup.gerenciador.tarefas.dtos;

import br.com.zup.gerenciador.tarefas.models.Status;
import br.com.zup.gerenciador.tarefas.models.Tarefa;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class TarefaDTO {
    @NotEmpty(message = "Nome não pode ficar vazio!")
    private String nome;

    @NotEmpty(message = "Descrição não pode ficar vazio!")
    private String descricao;

    @NotNull(message = "Prazo não pode ser nulo")
    private LocalDate prazo;

    @Email(message = "Email inválido!")
    @NotEmpty(message = "Email não pode ficar vazio!")
    private String emailUsuario;

    public TarefaDTO() {
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

    public Tarefa converterParaTarefa() {
        Tarefa tarefa = new Tarefa();

        tarefa.setNome(nome);
        tarefa.setDescricao(descricao);
        tarefa.setEmailUsuario(emailUsuario);
        tarefa.setPrazo(prazo);

        return tarefa;
    }
}
