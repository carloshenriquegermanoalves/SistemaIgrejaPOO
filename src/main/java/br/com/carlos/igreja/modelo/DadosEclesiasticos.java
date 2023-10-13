package br.com.carlos.igreja.modelo;

import java.io.Serializable;
import java.util.Objects;

public class DadosEclesiasticos implements Serializable {
    private boolean batizado;
    private boolean dizimista;
    private boolean casado;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DadosEclesiasticos that)) return false;
        return isBatizado() == that.isBatizado() && isDizimista() == that.isDizimista() && isCasado() == that.isCasado();
    }

    @Override
    public int hashCode() {
        return Objects.hash(isBatizado(), isDizimista(), isCasado());
    }

    public boolean isBatizado() {
        return batizado;
    }

    public void setBatizado(boolean batizado) {
        this.batizado = batizado;
    }

    public boolean isDizimista() {
        return dizimista;
    }

    public void setDizimista(boolean dizimista) {
        this.dizimista = dizimista;
    }

    public boolean isCasado() {
        return casado;
    }

    public void setCasado(boolean casado) {
        this.casado = casado;
    }

    public DadosEclesiasticos(boolean batizado, boolean dizimista, boolean casado) {
        this.batizado = batizado;
        this.dizimista = dizimista;
        this.casado = casado;
    }
}
