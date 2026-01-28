package org.mm.UD3.Recuperación_HundirLaFlota;

import java.util.List;

public class Tablero {
    private List<Barco> barcos;

    public Tablero(List<Barco> barcos) {
        this.barcos = barcos;
    }

    public List<Barco> getBarcos() {
        return barcos;
    }

    public void setBarcos(List<Barco> barcos) {
        this.barcos = barcos;
    }

    @Override
    public String toString() {
        return "Tablero{" +
                "barcos=" + barcos +
                '}';
    }
}
