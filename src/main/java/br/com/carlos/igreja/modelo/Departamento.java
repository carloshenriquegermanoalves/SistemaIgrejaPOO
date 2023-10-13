package br.com.carlos.igreja.modelo;

import java.io.Serializable;
import java.util.List;

public record Departamento(String nomeDepartamento, List<Pessoa> membrosDoDepartamento) implements Serializable {

}

