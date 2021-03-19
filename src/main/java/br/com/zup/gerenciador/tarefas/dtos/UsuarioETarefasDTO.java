package br.com.zup.gerenciador.tarefas.dtos;

import br.com.zup.gerenciador.tarefas.models.Usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UsuarioETarefasDTO {
    @NotEmpty
    private String nome;

    @NotEmpty
    @Email
    private String email;

    @NotNull
    private ListaDeTarefasDTO tarefas;

    public UsuarioETarefasDTO() {
    }

    public UsuarioETarefasDTO(String nome, String email, ListaDeTarefasDTO tarefas) {
        this.nome = nome;
        this.email = email;
        this.tarefas = tarefas;
    }

    public UsuarioETarefasDTO(Usuario usuario, ListaDeTarefasDTO tarefas) {
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.tarefas = tarefas;
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

    public ListaDeTarefasDTO getTarefas() {
        return tarefas;
    }

    public void setTarefas(ListaDeTarefasDTO tarefas) {
        this.tarefas = tarefas;
    }
}
