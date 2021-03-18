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
public class UsuariosService {
    private List<Usuario> usuarios;

    @Autowired
    TarefaService tarefaService;

    public UsuariosService() {
        usuarios = new ArrayList<>();
    }

    public Usuario adicionarUsuario(Usuario usuario) {
        emailRepetido(usuario.getEmail());
        usuarios.add(usuario);
        return usuario;
    }

    public List<Usuario> listarUsuarios() {
        return usuarios;
    }

    public Usuario pesquisarUsuarioPeloEmail(String email) {
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equalsIgnoreCase(email)) {
                return usuario;
            }
        }

        throw new UsuarioNaoEncontradoException();
    }

    public void deletarUsuarioPeloEmail(String email) {
        Usuario usuario = pesquisarUsuarioPeloEmail(email);
        List<List<Tarefa>> tarefa = tarefaService.pesquisarTarefasDoUsuario(email);
        if(tarefa.get(0).size() <=0){
            usuarios.remove(usuario);
        }

        throw new RuntimeException("Usuário possui tarefas não concluidas");
    }

    public void emailRepetido(String email){
        for (Usuario usuario : usuarios) {
            if(usuario.getEmail().equalsIgnoreCase(email)){
                throw new EmailRepetidoException();
            }
        }
    }
}
