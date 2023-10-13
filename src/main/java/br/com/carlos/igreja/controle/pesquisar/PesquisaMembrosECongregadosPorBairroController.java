package br.com.carlos.igreja.controle.pesquisar;

import br.com.carlos.igreja.modelo.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.stream.*;

public class PesquisaMembrosECongregadosPorBairroController implements ActionListener {
    private final SistemaIgreja sistemaIgreja;

    public PesquisaMembrosECongregadosPorBairroController(SistemaIgreja sistemaIgreja) {
        this.sistemaIgreja = sistemaIgreja;
    }

    public void pesquisaPorBairro() {
        try {
            String nomeBairro = JOptionPane.showInputDialog("Digite o nome do bairro para pesquisa: ");
            List<Pessoa> pessoasBairroPesquisada = sistemaIgreja.pesquisaPessoasPorBairro(nomeBairro, sistemaIgreja.getPessoas());

            if (!pessoasBairroPesquisada.isEmpty()) {
                String membrosBairroMensagem = pessoasBairroPesquisada.stream().map(Pessoa::getNome).collect(Collectors.joining("\n"));
                String mensagemUsuario = "Os membros que moram no bairro " + nomeBairro + " são:\n" + membrosBairroMensagem;
                JOptionPane.showMessageDialog(null, mensagemUsuario);
            } else
                JOptionPane.showMessageDialog(null, "Não há membros morando no bairro " + nomeBairro);
        }

        catch (Exception excecao) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro durante a pesquisa: " + excecao.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        pesquisaPorBairro();
    }
}
