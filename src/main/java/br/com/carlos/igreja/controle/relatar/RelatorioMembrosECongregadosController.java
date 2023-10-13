package br.com.carlos.igreja.controle.relatar;

import br.com.carlos.igreja.modelo.Pessoa;
import br.com.carlos.igreja.modelo.SistemaIgreja;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.function.Function;

public class RelatorioMembrosECongregadosController implements ActionListener {

    private final SistemaIgreja sistemaIgreja;

    public RelatorioMembrosECongregadosController(SistemaIgreja sistemaIgreja) {
        this.sistemaIgreja = sistemaIgreja;
    }

    public void menuRelatorioPessoas() {
        List<Pessoa> todasPessoasCadastradas = sistemaIgreja.getPessoas();
        String opcaoMenuRelatorioMembrosCongregados = JOptionPane.showInputDialog("""
                Digite a opção desejada:
                1.Lista de Membros
                2.Lista de Congregados
                3.Lista de Membros e Congregados
                """);
        switch (opcaoMenuRelatorioMembrosCongregados) {
            case "1" -> sistemaIgreja.mostraPessoasNaMensagem(todasPessoasCadastradas, sistemaIgreja::todosOsMembros, "membros");
            case "2" -> sistemaIgreja.mostraPessoasNaMensagem(todasPessoasCadastradas, sistemaIgreja::todosOsCongregados, "congregados");
            case "3" -> sistemaIgreja.mostraPessoasNaMensagem(todasPessoasCadastradas, Function.identity(), "membros e congregados");
            default -> JOptionPane.showMessageDialog(null, "Opção inválida! Tente novamente!");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        menuRelatorioPessoas();
    }
}
