package br.com.zup.gerenciador.tarefas.services;

import br.com.zup.gerenciador.tarefas.exceptions.EmailRepetidoException;
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
        usuarios.remove(usuario);
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
}
