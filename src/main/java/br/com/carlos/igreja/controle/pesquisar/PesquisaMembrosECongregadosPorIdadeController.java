package br.com.carlos.igreja.controle.pesquisar;

import br.com.carlos.igreja.modelo.Pessoa;
import br.com.carlos.igreja.modelo.SistemaIgreja;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

public class PesquisaMembrosECongregadosPorIdadeController implements ActionListener {
    private final SistemaIgreja sistemaIgreja;

    public PesquisaMembrosECongregadosPorIdadeController(SistemaIgreja sistemaIgreja) {
        this.sistemaIgreja = sistemaIgreja;
    }

    public void pesquisaPorIdade() {
        try {
            String input = JOptionPane.showInputDialog("Digite a idade do membro para pesquisa: ");

            if (input != null && !input.isEmpty()) {
                int idadeMembro = Integer.parseInt(input);
                List<Pessoa> pessoasIdadePesquisada = sistemaIgreja.pesquisaPessoasPorIdade(idadeMembro, sistemaIgreja.getPessoas());

                String membrosIdadeMessagem = pessoasIdadePesquisada.stream().map(Pessoa::getNome).collect(Collectors.joining("\n"));

                String mensagemUsuario = !pessoasIdadePesquisada.isEmpty()
                        ? "Os membros com " + idadeMembro + " anos são:\n" + membrosIdadeMessagem
                        : "Não há membros com " + idadeMembro + " anos";

                JOptionPane.showMessageDialog(null, mensagemUsuario);
            } else {
                JOptionPane.showMessageDialog(null, "Nenhuma idade fornecida. Tente novamente.");
            }
        } catch (NumberFormatException excecao) {
            JOptionPane.showMessageDialog(null, "Digite apenas números válidos!");
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        pesquisaPorIdade();
    }
}
