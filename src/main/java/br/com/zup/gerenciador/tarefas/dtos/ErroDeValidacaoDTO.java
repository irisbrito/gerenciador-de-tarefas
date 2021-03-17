package br.com.zup.gerenciador.tarefas.dtos;

public class ErroDeValidacaoDTO {
    private String campo;
    private String mensagem;

    public ErroDeValidacaoDTO() {
    }

    public ErroDeValidacaoDTO(String campo, String mensagem) {
        this.campo = campo;
        this.mensagem = mensagem;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
