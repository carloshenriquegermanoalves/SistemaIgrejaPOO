package br.com.carlos.igreja.controle.pesquisar;

import br.com.carlos.igreja.modelo.Pessoa;
import br.com.carlos.igreja.modelo.SistemaIgreja;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

public class PesquisaMembrosECongregadosPorRuaController implements ActionListener {
    private final SistemaIgreja sistemaIgreja;

    public PesquisaMembrosECongregadosPorRuaController(SistemaIgreja sistemaIgreja) {
        this.sistemaIgreja = sistemaIgreja;
    }

    public void pesquisaPorRua() {
        try {
            String nomeRua = JOptionPane.showInputDialog("Digite o nome da rua para pesquisa: ");

            if (nomeRua != null && !nomeRua.isEmpty()) {
                List<Pessoa> pessoasRuaPesquisada = sistemaIgreja.pesquisaPessoasPorRua(nomeRua, sistemaIgreja.getPessoas());

                if (!pessoasRuaPesquisada.isEmpty()) {
                    String membrosRuaMensagem = pessoasRuaPesquisada.stream()
                            .map(Pessoa::getNome)
                            .collect(Collectors.joining("\n"));

                    String mensagemUsuario = "Os membros que moram na rua " + nomeRua + " são:\n" + membrosRuaMensagem;
                    JOptionPane.showMessageDialog(null, mensagemUsuario);
                } else {
                    JOptionPane.showMessageDialog(null, "Não há membros morando na rua " + nomeRua);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Nenhuma rua fornecida. Tente novamente.");
            }
        } catch (Exception excecao) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro durante a pesquisa: " + excecao.getMessage());
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        pesquisaPorRua();
    }
}
