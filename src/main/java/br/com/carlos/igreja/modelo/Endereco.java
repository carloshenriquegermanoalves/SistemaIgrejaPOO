package br.com.carlos.igreja.modelo;

import java.io.Serializable;

public class Endereco implements Serializable {

    private final String logradouro;
    private final String numero;
    private final String bairro;
    private final String cidade;
    private final String estado;

    public Endereco(String logradouro, String numero,
                    String bairro, String cidade, String estado) {
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
    }

    public String getLogradouro() {
        return logradouro;
    }

    @Override
    public String toString() {
        return "Endereco [logradouro=" + logradouro +
                ", numero=" + numero + ", bairro=" + bairro +
                ", cidade=" + cidade
                + ", estado=" + estado + "]";
    }
    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

}
