package br.com.carlos.igreja.uteis;

import br.com.carlos.igreja.uteis.excecoes.DataNaoFormatadaException;
import br.com.carlos.igreja.uteis.excecoes.TelefoneNaoFormatadoException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public abstract class Formatacao {

    public static LocalDate formataDataDeNascimento(String data) throws DataNaoFormatadaException {
        String dataNumerica = data.replaceAll("[^0-9]", ""); // Remove caracteres não numéricos
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        try {
            return LocalDate.parse(dataNumerica, formatter);
        } catch (DateTimeParseException e) {
            throw new DataNaoFormatadaException("Formato de data inválido. Use o formato dd/mm/yyyy.");
        }
    }

}
