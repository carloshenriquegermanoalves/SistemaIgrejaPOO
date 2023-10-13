package br.com.carlos.igreja.controle.pesquisar;

import br.com.carlos.igreja.modelo.Pessoa;
import br.com.carlos.igreja.modelo.SistemaIgreja;
import br.com.carlos.igreja.modelo.excecoes.PessoaNaoCadastradaException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PesquisaMembrosECongregadosPorNomeController implements ActionListener {
    private final SistemaIgreja sistemaIgreja;

    public PesquisaMembrosECongregadosPorNomeController(SistemaIgreja sistemaIgreja) {
        this.sistemaIgreja = sistemaIgreja;
    }

    public void pesquisaPorNome() {
        String nomeMembroParaPesquisa = JOptionPane.showInputDialog("Digite o nome do membro para pesquisa: ");
        try {
            Pessoa pessoa = sistemaIgreja.pesquisaPessoaPorNome(nomeMembroParaPesquisa, sistemaIgreja.getPessoas());
            JOptionPane.showMessageDialog(null, pessoa.toString());
        } catch (PessoaNaoCadastradaException excecao) {
            JOptionPane.showMessageDialog(null, "A pessoa " + nomeMembroParaPesquisa + " não foi encontrada!");

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        pesquisaPorNome();
    }
}
