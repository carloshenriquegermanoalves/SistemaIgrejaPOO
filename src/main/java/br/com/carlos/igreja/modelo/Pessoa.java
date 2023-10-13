package br.com.carlos.igreja.modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public class Pessoa implements Comparable<Pessoa>, Serializable {

    private String nome;
    private final LocalDate dataDeNascimento;
    private Endereco endereco;
    private final String pai;
    private final String mae;
    private DadosEclesiasticos dadosEclesiasticos;

    public Pessoa(String nome, String genero, LocalDate dataDeNascimento,
                  Endereco endereco,
                  String pai, String mae,
                  DadosEclesiasticos dadosEclesiasticos) {
        this.nome = nome;
        this.dataDeNascimento = dataDeNascimento;
        this.endereco = endereco;
        this.pai = pai;
        this.mae = mae;
        this.dadosEclesiasticos = dadosEclesiasticos;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataDeNascimento() {
        return this.dataDeNascimento;
    }

    public Endereco getEndereco() {
        return this.endereco;
    }

    public DadosEclesiasticos getDadosEclesiasticos() {
        return this.dadosEclesiasticos;
    }

    public String getPai() {
        return this.pai;
    }

    public String getMae() {
        return this.mae;
    }

    public int getIdadeDaPessoa() {
        LocalDate dataAtual = LocalDate.now();
        Period periodo = Period.between(dataDeNascimento, dataAtual);
        return periodo.getYears();
    }


    public String toString() {
        String tipo = dadosEclesiasticos.isBatizado() ? "membro" : "congregado";
        return String.format("O %s %s mora em: %s, no bairro %s e na rua: %s\nTem %d anos",
                tipo, this.nome, endereco.getCidade(), endereco.getBairro(), endereco.getLogradouro(), getIdadeDaPessoa());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pessoa pessoa)) return false;
        return getNome().equals(pessoa.getNome()) && getDataDeNascimento().equals(pessoa.getDataDeNascimento())
                && getEndereco().equals(pessoa.getEndereco()) && getPai().equals(pessoa.getPai())
                && getMae().equals(pessoa.getMae());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNome(), getDataDeNascimento(), getEndereco(), getPai(), getMae());
    }

    @Override
    public int compareTo(Pessoa outraPessoa) {
        return this.getDataDeNascimento().compareTo(outraPessoa.getDataDeNascimento());
    }

}
