package br.com.carlos.igreja.uteis.excecoes;

public class TelefoneNaoFormatadoException extends Exception{

    public TelefoneNaoFormatadoException(String mensagem) {
        super(mensagem);
    }

}