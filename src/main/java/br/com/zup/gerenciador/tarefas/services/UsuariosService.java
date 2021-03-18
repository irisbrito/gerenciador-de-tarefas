package br.com.zup.gerenciador.tarefas.services;

import br.com.zup.gerenciador.tarefas.exceptions.UsuarioNaoEncontradoException;
import br.com.zup.gerenciador.tarefas.models.Usuario;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuariosService {
    private List<Usuario> usuarios;

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
        usuarios.remove(usuario);
    }

    public void emailRepetido(String email){
        for (Usuario usuario : usuarios) {
            if(usuario.getEmail().equalsIgnoreCase(email)){
                throw new RuntimeException("Email repetido!");
            }
        }
    }
}
