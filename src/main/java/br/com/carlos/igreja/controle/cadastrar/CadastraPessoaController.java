package br.com.carlos.igreja.controle.cadastrar;

import br.com.carlos.igreja.modelo.DadosEclesiasticos;
import br.com.carlos.igreja.modelo.Endereco;
import br.com.carlos.igreja.modelo.Pessoa;
import br.com.carlos.igreja.modelo.SistemaIgreja;
import br.com.carlos.igreja.uteis.Formatacao;
import br.com.carlos.igreja.uteis.excecoes.DataNaoFormatadaException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;

public class CadastraPessoaController implements ActionListener {
    private final SistemaIgreja sistemaIgreja;

    public CadastraPessoaController(SistemaIgreja sistemaIgreja) {
        this.sistemaIgreja = sistemaIgreja;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        cadastraMembroNaIgreja(sistemaIgreja);
    }

    public void cadastraMembroNaIgreja(SistemaIgreja sistemaIgreja) {
        String nome = JOptionPane.showInputDialog("Digite o nome completo: ");
        if (pessoaJaExiste(nome)) {
            JOptionPane.showMessageDialog(null, "A pessoa " + nome + " já está cadastrada!");
            return;
        }
        String genero = JOptionPane.showInputDialog("Digite o gênero de " + nome + ": ");
        String stringDataDeNascimento = JOptionPane.showInputDialog("Digite a data de nascimento de " + nome + " (formato: dd/mm/yyyy): ");
        LocalDate dataDeNascimento;
        try {
            dataDeNascimento = Formatacao.formataDataDeNascimento(stringDataDeNascimento);
        } catch (DataNaoFormatadaException e) {
            JOptionPane.showMessageDialog(null, "Formato de data inválido! Tente novamente!");
            return;
        }
        String logradouro = JOptionPane.showInputDialog("Digite o nome da rua: ");
        String numero = JOptionPane.showInputDialog("Digite o número da casa: ");
        String bairro = JOptionPane.showInputDialog("Digite o nome do bairro: ");
        String cidade = JOptionPane.showInputDialog("Digite o nome da cidade: ");
        String estado = JOptionPane.showInputDialog("Digite o nome do estado: ");
        Endereco endereco = new Endereco(logradouro, numero, bairro, cidade, estado);
        String pai = JOptionPane.showInputDialog("Digite o nome do pai: ");
        String mae = JOptionPane.showInputDialog("Digite o nome da mãe: ");
        boolean ehBatizado = JOptionPane.showInputDialog(nome + " já é batizado? ").equalsIgnoreCase("sim");
        boolean ehDizimista = JOptionPane.showInputDialog(nome + " é dizimista? ").equalsIgnoreCase("sim");
        boolean ehCasado = JOptionPane.showInputDialog(nome + " é casado?").equalsIgnoreCase("sim");
        DadosEclesiasticos dadosEclesiasticos = new DadosEclesiasticos(ehBatizado, ehDizimista, ehCasado);
        Pessoa pessoa = new Pessoa(nome, genero, dataDeNascimento, endereco, pai, mae, dadosEclesiasticos);
        sistemaIgreja.salvarDadosPessoa(pessoa);
    }

    private boolean pessoaJaExiste(String nome) {
        List<Pessoa> membrosCadastrados = sistemaIgreja.getPessoas();
        return membrosCadastrados.stream().anyMatch(pessoa -> pessoa.getNome().equalsIgnoreCase(nome));
    }

}