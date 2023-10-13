package br.com.carlos.igreja.modelo;

import br.com.carlos.igreja.modelo.excecoes.PessoaNaoCadastradaException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SistemaIgrejaTest {

    SistemaIgreja sistemaIgreja = new SistemaIgreja();
    List<Pessoa> pessoas = sistemaIgreja.getPessoas();
    LocalDate dataDeNascimento = LocalDate.of(2004, 12, 7);
    Endereco endereco = new Endereco("José", "102", "Jader", "Guarabira", "Paraíba");
    DadosEclesiasticos dadosEclesiasticos = new DadosEclesiasticos(true, true, false);
    Pessoa pessoa = new Pessoa("Carlos", "Masculino", dataDeNascimento,
            endereco, "Adailton", "Eliane", dadosEclesiasticos);

    @Test
    void cadastraPessoa() {
        sistemaIgreja.salvarDadosPessoa(pessoa);
        try {
            Pessoa pessoaCadastrada = sistemaIgreja.pesquisaPessoaPorNome(pessoa.getNome(), sistemaIgreja.getPessoas());
            assertNotNull(pessoaCadastrada);
        } catch (PessoaNaoCadastradaException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void pesquisaPessoaPorNome() {
        String nomeMembroParaPesquisar = "Carlos";
        Pessoa pessoaEncontrada;
        try {
            pessoaEncontrada = sistemaIgreja.pesquisaPessoaPorNome(nomeMembroParaPesquisar, pessoas);
            assertEquals(nomeMembroParaPesquisar, pessoaEncontrada.getNome());
        } catch (PessoaNaoCadastradaException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void pesquisaPessoasPorRua() {
        String nomeRuaParaPesquisar = "José";
        List<Pessoa> pessoasDaRua = sistemaIgreja.pesquisaPessoasPorRua(nomeRuaParaPesquisar, pessoas);
        assertEquals(nomeRuaParaPesquisar, pessoasDaRua.get(0).getEndereco().getLogradouro());
    }

    @Test
    void pesquisaPessoasPorBairro() {
        String nomeBairroParaPesquisar = "Jader";
        List<Pessoa> pessoasDoBairro = sistemaIgreja.pesquisaPessoasPorBairro(nomeBairroParaPesquisar, pessoas);
        assertEquals(nomeBairroParaPesquisar, pessoasDoBairro.get(0).getEndereco().getBairro());
    }

    @Test
    void pesquisaPessoasPorCidade() {
        String nomeCidadeParaPesquisar = "Guarabira";
        List<Pessoa> pessoasDaCidade = sistemaIgreja.pesquisaPessoasPorCidade(nomeCidadeParaPesquisar, pessoas);
        assertEquals(nomeCidadeParaPesquisar, pessoasDaCidade.get(0).getEndereco().getCidade());
    }

    @Test
    void pesquisaPessoasPorEstado() {
        String nomeEstadoParaPesquisar = "Paraíba";
        List<Pessoa> pessoasDoEstado = sistemaIgreja.pesquisaPessoasPorEstado(nomeEstadoParaPesquisar, pessoas);
        assertEquals(nomeEstadoParaPesquisar, pessoasDoEstado.get(0).getEndereco().getEstado());
    }

    @Test
    void pesquisaPessoaPorIdade() {
        int idadeEsperada = Period.between(dataDeNascimento, LocalDate.now()).getYears();
        int idadeReal = pessoa.getIdadeDaPessoa();
        assertEquals(idadeEsperada, idadeReal);
    }

}