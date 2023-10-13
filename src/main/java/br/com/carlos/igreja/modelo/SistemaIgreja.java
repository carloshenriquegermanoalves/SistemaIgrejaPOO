package br.com.carlos.igreja.modelo;

import br.com.carlos.igreja.modelo.excecoes.PessoaNaoCadastradaException;

import javax.swing.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SistemaIgreja implements InterfaceIgreja {

    List<Pessoa> listaDePessoas = new ArrayList<>();
    GravadorDeDados gravadorDeDados = new GravadorDeDados();

    public void salvarDadosPessoa(Pessoa pessoa){
        try {
            gravadorDeDados.salvarDadosPessoas(pessoa);
        } catch(Exception e){
            System.err.println(e.getMessage());
        }
    }


    @Override
    public int idadeDoMembro(LocalDate dataDeNascimento, LocalDate dataDoSistema) {
        Period periodo = Period.between(dataDeNascimento, dataDoSistema);
        JOptionPane.showMessageDialog(null, dataDeNascimento);
        JOptionPane.showMessageDialog(null, dataDoSistema);

        JOptionPane.showMessageDialog(null, dataDoSistema.getYear() - dataDeNascimento.getYear());
        JOptionPane.showMessageDialog(null, periodo.getYears());
        return periodo.getYears();
    }


    @Override
    public List<Pessoa> getPessoas() {
        try {
            this.listaDePessoas = gravadorDeDados.recuperarDadosPessoas();
        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Não foi possível recuperar os dados");
        }
        return this.listaDePessoas;
    }


    @Override
    public Pessoa pesquisaPessoaPorNome(String nome, List<Pessoa> pessoasRecuperadas) throws PessoaNaoCadastradaException {
        pessoasRecuperadas.forEach(p -> JOptionPane.showMessageDialog(null, p.getNome()));

        return pessoasRecuperadas.stream()
                .filter(pessoa -> pessoa.getNome().equals(nome))
                .findFirst()
                .orElseThrow(() -> new PessoaNaoCadastradaException("Pessoa não encontrada na lista"));
    }

    @Override
    public List<Pessoa> todosOsCongregados(List<Pessoa> pessoas) {
        return pessoas.stream().filter(pessoa -> !pessoa.getDadosEclesiasticos().isBatizado()).toList();
    }

    @Override
    public List<Pessoa> todosOsMembros(List<Pessoa> pessoas) {
        return pessoas.stream().filter(pessoa -> pessoa.getDadosEclesiasticos().isBatizado()).collect(Collectors.toList());
    }

    @Override
    public List<Pessoa> todosOsDizimistas() {
        return getPessoas().stream().filter(pessoa -> pessoa.getDadosEclesiasticos().isDizimista()).collect(Collectors.toList());
    }

    @Override
    public List<Pessoa> pesquisaPessoasPorIdade(int idade, List<Pessoa> pessoasRecuperadas) {
        LocalDate dataDoSistema = LocalDate.now();
        return pessoasRecuperadas.stream().filter(pessoa -> idadeDoMembro(pessoa.getDataDeNascimento(), dataDoSistema) == idade).collect(Collectors.toList());
    }


    @Override
    public List<Pessoa> pesquisaPessoasPorRua(String rua, List<Pessoa> pessoasRecuperadas) {
        return pessoasRecuperadas.stream().filter(pessoa -> pessoa.getEndereco().getLogradouro().equals(rua)).toList();
    }

    @Override
    public List<Pessoa> pesquisaPessoasPorBairro(String bairro, List<Pessoa> pessoasRecuperadas) {
        return pessoasRecuperadas.stream().filter(pessoa -> pessoa.getEndereco().getBairro().equals(bairro)).toList();
    }

    @Override
    public List<Pessoa> pesquisaPessoasPorCidade(String cidade, List<Pessoa> pessoasRecuperadas) {
        return pessoasRecuperadas.stream().filter(pessoa -> pessoa.getEndereco().getCidade().equals(cidade)).toList();
    }

    @Override
    public List<Pessoa> pesquisaPessoasPorEstado(String estado, List<Pessoa> pessoasRecuperadas) {
        return pessoasRecuperadas.stream().filter(pessoa -> pessoa.getEndereco().getEstado().equals(estado)).toList();
    }

    @Override
    public void mostraPessoasNaMensagem(List<Pessoa> pessoas, Function<List<Pessoa>, List<Pessoa>> filterFunction, String descricao) {
        List<Pessoa> filteredPessoas = filterFunction.apply(pessoas);
        if (!filteredPessoas.isEmpty()) {
            StringJoiner pessoasMessage = new StringJoiner("\n");
            for (Pessoa pessoa : filteredPessoas)
                pessoasMessage.add(pessoa.getNome());
            JOptionPane.showMessageDialog(null, "Todos os " + descricao + " são:\n" + pessoasMessage);
        } else
            JOptionPane.showMessageDialog(null, "Não há " + descricao + " cadastrados!");
    }

}
