package br.com.carlos.igreja.visao;

import br.com.carlos.igreja.controle.cadastrar.*;
import br.com.carlos.igreja.controle.pesquisar.*;
import br.com.carlos.igreja.controle.relatar.*;
import br.com.carlos.igreja.modelo.*;

import javax.swing.*;
import java.awt.*;

import static java.awt.Color.*;

public class IgrejaGUI extends JFrame {

    JLabel linha1, linha2;
    ImageIcon logoIgrejaImagem = new ImageIcon("./imagens/logo.jpg");
    SistemaIgreja sistemaIgreja = new SistemaIgreja();
    JMenuBar barraDeMenu = new JMenuBar();

    public IgrejaGUI() {
        setTitle("Sistema Igreja!");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setBackground(WHITE);
        getContentPane().setBackground(BLACK);
        linha1 = new JLabel("Sistema de Gerenciamento de Membresia da Igreja!", JLabel.CENTER);
        linha1.setForeground(YELLOW);
        linha1.setFont(new Font("Serif", Font.BOLD, 24));
        linha2 = new JLabel(logoIgrejaImagem, JLabel.CENTER);
        setLayout(new GridLayout(3, 1));
        add(linha1);
        add(linha2);
        add(new JLabel());
        JMenu areaDeMembros = criarMenuMembro();
        JMenu areaDeRelatorios = criarMenuRelatorio();
        JMenu areaDePesquisas = criarMenuPesquisa();
        geraMenuPrincipal(areaDeMembros, areaDeRelatorios, areaDePesquisas);
    }

    public JMenu criarMenuMembro() {
        JMenu menuMembros = new JMenu("Área de Membros");
        JMenuItem cadastrarMembro = new JMenuItem("Cadastrar Membro/Congregado");

        cadastrarMembro.addActionListener(new CadastraPessoaController(sistemaIgreja));
        menuMembros.add(cadastrarMembro);

        return menuMembros;
    }

    public JMenu criarMenuRelatorio() {
        JMenu areaDeRelatorio = new JMenu("Relatórios");

        JMenuItem relatorioMembrosECongregados = new JMenuItem("Membros e/ou congregados");
        relatorioMembrosECongregados.addActionListener(new RelatorioMembrosECongregadosController(sistemaIgreja));

        JMenuItem relatorioFinancas = new JMenuItem("Finanças");
        relatorioFinancas.addActionListener(new RelatorioFinancasController(sistemaIgreja));

        areaDeRelatorio.add(relatorioMembrosECongregados);
        areaDeRelatorio.add(relatorioFinancas);

        return areaDeRelatorio;
    }

    public JMenu criarMenuPesquisa() {
        JMenu areaDePesquisa = new JMenu("Pesquisas");

        JMenuItem pesquisaPorNome = new JMenuItem("Membros/congregados por nome");
        pesquisaPorNome.addActionListener(new PesquisaMembrosECongregadosPorNomeController(sistemaIgreja));

        JMenuItem pesquisaPorIdade = new JMenuItem("Membros/congregados por idade");
        pesquisaPorIdade.addActionListener(new PesquisaMembrosECongregadosPorIdadeController(sistemaIgreja));

        JMenuItem pesquisaPorRua = new JMenuItem("Membros/congregados por nome da rua");
        pesquisaPorRua.addActionListener(new PesquisaMembrosECongregadosPorRuaController(sistemaIgreja));

        JMenuItem pesquisaPorBairro = new JMenuItem("Membros/congregados por nome do bairro");
        pesquisaPorBairro.addActionListener(new PesquisaMembrosECongregadosPorBairroController(sistemaIgreja));

        JMenuItem pesquisaPorCidade = new JMenuItem("Membros/congregados por nome da cidade");
        pesquisaPorCidade.addActionListener(new PesquisaMembrosECongregadosPorCidadeController(sistemaIgreja));

        JMenuItem pesquisaPorEstado = new JMenuItem("Membros/congregados por nome do estado");
        pesquisaPorEstado.addActionListener(new PesquisaMembrosECongregadosPorEstadoController(sistemaIgreja));

        areaDePesquisa.add(pesquisaPorNome);
        areaDePesquisa.add(pesquisaPorIdade);
        areaDePesquisa.add(pesquisaPorRua);
        areaDePesquisa.add(pesquisaPorBairro);
        areaDePesquisa.add(pesquisaPorCidade);
        areaDePesquisa.add(pesquisaPorEstado);

        return areaDePesquisa;
    }

    public void geraMenuPrincipal(JMenu areaDeMembros, JMenu areaDeRelatorios, JMenu areaDePesquisas) {
        barraDeMenu.add(areaDeMembros);
        barraDeMenu.add(areaDeRelatorios);
        barraDeMenu.add(areaDePesquisas);
        setJMenuBar(barraDeMenu);
    }

}