package br.com.zup.gerenciador.tarefas.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

public class Usuario {
    @NotEmpty(message = "Nome do usuário não pode estar vazio")
    private String nome;

    @NotEmpty(message = "E-mail do usuário está vazio")
    @Email(message = "Email do usuário inválido")
    private String email;

    private List<Tarefa> tarefas = new ArrayList<>();

    public Usuario() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Tarefa> getTarefas() {
        return tarefas;
    }

    public void setTarefas(List<Tarefa> tarefas) {
      this.tarefas = tarefas;
    }
}