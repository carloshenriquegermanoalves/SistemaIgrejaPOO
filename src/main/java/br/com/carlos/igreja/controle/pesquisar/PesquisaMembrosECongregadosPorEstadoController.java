package br.com.carlos.igreja.controle.pesquisar;

import br.com.carlos.igreja.modelo.Pessoa;
import br.com.carlos.igreja.modelo.SistemaIgreja;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

public class PesquisaMembrosECongregadosPorEstadoController implements ActionListener {
    private final SistemaIgreja sistemaIgreja;

    public PesquisaMembrosECongregadosPorEstadoController(SistemaIgreja sistemaIgreja) {
        this.sistemaIgreja = sistemaIgreja;
    }

    public void pesquisaPorEstado() {
        try {
            String nomeEstado = JOptionPane.showInputDialog("Digite o nome do estado para pesquisa: ");
            List<Pessoa> pessoasEstadoPesquisado = sistemaIgreja.pesquisaPessoasPorEstado(nomeEstado, sistemaIgreja.getPessoas());

            if (!pessoasEstadoPesquisado.isEmpty()) {
                String membrosEstadoMensagem = pessoasEstadoPesquisado.stream()
                        .map(Pessoa::getNome) // Obtém o nome das pessoas em vez do nome da cidade
                        .collect(Collectors.joining("\n"));

                String mensagemUsuario = "Os membros que moram no estado " + nomeEstado + " são:\n" + membrosEstadoMensagem;
                JOptionPane.showMessageDialog(null, mensagemUsuario);
            } else {
                JOptionPane.showMessageDialog(null, "Não há membros morando no estado " + nomeEstado);
            }
        } catch (Exception excecao) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro durante a pesquisa: " + excecao.getMessage());
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
     pesquisaPorEstado();
    }
}
