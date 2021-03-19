package br.com.zup.gerenciador.tarefas.services;

import br.com.zup.gerenciador.tarefas.exceptions.EmailRepetidoException;
import br.com.zup.gerenciador.tarefas.exceptions.UsuarioNaoEncontradoException;
import br.com.zup.gerenciador.tarefas.models.Tarefa;
import br.com.zup.gerenciador.tarefas.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {
    private List<Usuario> usuarios;

    @Autowired
    TarefaService tarefaService;

    public UsuarioService() {
        usuarios = new ArrayList<>();
    }

    /**
     * Adiciona um usuário ao sistema
     *
     * @param usuario o usuário a ser adicionado
     * @return usuario
     */
    public Usuario adicionarUsuario(Usuario usuario) {
        emailRepetido(usuario.getEmail());
        usuarios.add(usuario);
        return usuario;
    }

    /**
     * Lista todos os usuários do sistema
     *
     * @return lista de usuários
     */
    public List<Usuario> listarUsuarios() {
        return usuarios;
    }

    /**
     * Pesquisa um usuário pelo seu e-mail
     *
     * @param email string com o e-mail a ser pesquisado
     * @return usuário
     */
    public Usuario pesquisarUsuarioPeloEmail(String email) {
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equalsIgnoreCase(email)) {
                return usuario;
            }
        }

        throw new UsuarioNaoEncontradoException();
    }

    /**
     * Deleta um usuário pelo e-mail especificado
     *
     * @param email string com o email
     */
    public void deletarUsuarioPeloEmail(String email) {
        Usuario usuario = pesquisarUsuarioPeloEmail(email);
        List<List<Tarefa>> tarefa = tarefaService.pesquisarTarefasDoUsuario(email);

        if(tarefa.get(0).size() <=0){
            usuarios.remove(usuario);
        } else {
            throw new RuntimeException("Usuário possui tarefas não concluidas");
        }
    }

    /**
     * Valida se o e-mail do usuário já está cadastrado no sistema
     *
     * @param email string com o e-mail do usuário
     */
    public void emailRepetido(String email){
        for (Usuario usuario : usuarios) {
            if(usuario.getEmail().equalsIgnoreCase(email)){
                throw new EmailRepetidoException();
            }
        }
    }

    /**
     * Pega as tarefas de um usuário específico
     *
     * @param email o email do usuário
     * @return lista contendo as listas de tarefas do usuário
     */
    public List <List <Tarefa>> pegarTarefasDoUsuario(String email) {
        return tarefaService.pesquisarTarefasDoUsuario(email);
    }
}
