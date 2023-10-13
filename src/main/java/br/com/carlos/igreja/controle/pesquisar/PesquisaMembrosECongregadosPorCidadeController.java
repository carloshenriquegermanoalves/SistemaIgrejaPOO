package br.com.carlos.igreja.controle.pesquisar;

import br.com.carlos.igreja.modelo.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.stream.*;

public class PesquisaMembrosECongregadosPorCidadeController implements ActionListener {
    private final SistemaIgreja sistemaIgreja;

    public PesquisaMembrosECongregadosPorCidadeController(SistemaIgreja sistemaIgreja) {
        this.sistemaIgreja = sistemaIgreja;
    }

    public void pesquisaPorCidade() {
        try {
            String nomeCidade = JOptionPane.showInputDialog("Digite o nome da cidade para pesquisa: ");
            List<Pessoa> pessoasCidadePesquisada = sistemaIgreja.pesquisaPessoasPorCidade(nomeCidade, sistemaIgreja.getPessoas());

            if (!pessoasCidadePesquisada.isEmpty()) {
                String membrosCidadeMensagem = pessoasCidadePesquisada.stream()
                        .map(Pessoa::getNome)
                        .collect(Collectors.joining("\n"));

                String mensagemUsuario = "Os membros que moram na cidade " + nomeCidade + " são:\n" + membrosCidadeMensagem;
                JOptionPane.showMessageDialog(null, mensagemUsuario);
            } else {
                JOptionPane.showMessageDialog(null, "Não há membros morando na cidade " + nomeCidade);
            }
        } catch (Exception excecao) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro durante a pesquisa: " + excecao.getMessage());
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        pesquisaPorCidade();
    }
}
