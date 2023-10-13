package br.com.carlos.igreja.modelo;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GravadorDeDados {

    private static final String ARQUIVO_MEMBROS = "membros.dat";

    public List<Pessoa> recuperarDadosPessoas() throws IOException, ClassNotFoundException {
        List<Pessoa> pessoas = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO_MEMBROS))) {
            while (true) {
                try {
                    Object obj = ois.readObject();
                    if (obj instanceof Pessoa pessoa) {
                        pessoas.add(pessoa);
                    } else {
                        System.err.println("Objeto não é uma instância de Pessoa, pulando.");
                    }
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("O arquivo de dados ainda não foi iniciado, pois essa é a primeira vez que se usa o programa" +
                    "ou o arquivo foi apagado");
        }
        return pessoas;
    }

    public void salvarDadosPessoas(Pessoa pessoa) throws IOException {
        List<Pessoa> pessoas = null;
        try {
            pessoas = recuperarDadosPessoas();
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, e);
        }

        assert pessoas != null;
        pessoas.add(pessoa);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO_MEMBROS))) {
            for (Pessoa p : pessoas) {
                oos.writeObject(p);
            }
            JOptionPane.showMessageDialog(null, "Dados salvos com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao salvar os dados do sistema: " + e.getMessage());
        }
    }

}

