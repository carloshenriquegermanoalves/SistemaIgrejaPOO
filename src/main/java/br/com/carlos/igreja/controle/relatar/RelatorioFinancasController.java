package br.com.carlos.igreja.controle.relatar;

import br.com.carlos.igreja.modelo.Pessoa;
import br.com.carlos.igreja.modelo.SistemaIgreja;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

public class RelatorioFinancasController implements ActionListener {
    private final SistemaIgreja sistemaIgreja;

    public RelatorioFinancasController(SistemaIgreja sistemaIgreja) {
        this.sistemaIgreja = sistemaIgreja;
    }

    public void relatorioFinancas() {
        try {
            String opcaoMenuRelatorioFinancas = JOptionPane.showInputDialog("Digite a opção desejada:\n" +
                    "1.Lista de membros dizimistas");

            if (opcaoMenuRelatorioFinancas.equals("1")) {
                List<Pessoa> pessoasDizimistas = sistemaIgreja.todosOsDizimistas();

                String membrosDizimistasMessage = pessoasDizimistas.isEmpty()
                        ? "Não há membros dizimistas"
                        : pessoasDizimistas.stream()
                        .map(Pessoa::getNome)
                        .collect(Collectors.joining("\n"));

                JOptionPane.showMessageDialog(null, "Membros dizimistas:\n" + membrosDizimistasMessage);
            }
        } catch (Exception excecao) {
            JOptionPane.showMessageDialog(null, "Erro ao exibir lista de membros dizimistas!");
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        relatorioFinancas();
    }

}
