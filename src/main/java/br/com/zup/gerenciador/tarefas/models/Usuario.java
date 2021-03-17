package br.com.zup.gerenciador.tarefas.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class Usuario {
    @NotEmpty(message = "Nome do usuário não pode estar vazio")
    private String nome;

    @NotEmpty(message = "E-mail do usuário está vazio")
    @Email(message = "Email do usuário inválido")
    private String email;

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

}