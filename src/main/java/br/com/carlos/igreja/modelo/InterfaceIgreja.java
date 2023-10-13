package br.com.carlos.igreja.modelo;

import br.com.carlos.igreja.modelo.excecoes.PessoaNaoCadastradaException;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Function;

public interface InterfaceIgreja {

    int idadeDoMembro(LocalDate dataDeNascimento, LocalDate dataDoSistema);
    List<Pessoa> getPessoas();
    Pessoa pesquisaPessoaPorNome(String nome, List<Pessoa> pessoasRecuperadas) throws PessoaNaoCadastradaException;
    List<Pessoa> todosOsCongregados(List<Pessoa> pessoas);
    List<Pessoa> todosOsMembros(List<Pessoa> pessoas);
    List<Pessoa> todosOsDizimistas();
    List<Pessoa> pesquisaPessoasPorIdade(int idade, List<Pessoa> pessoasRecuperadas);
    List<Pessoa> pesquisaPessoasPorRua(String rua, List<Pessoa> pessoasRecuperadas);
    List<Pessoa> pesquisaPessoasPorBairro(String Bairro, List<Pessoa> pessoasRecuperadas);
    List<Pessoa> pesquisaPessoasPorCidade(String cidade, List<Pessoa> pessoasRecuperadas);
    List<Pessoa> pesquisaPessoasPorEstado(String estado, List<Pessoa> pessoasRecuperadas);
    void mostraPessoasNaMensagem(List<Pessoa> pessoas, Function<List<Pessoa>, List<Pessoa>> filterFunction, String descricao);
}

