package org.mm.UD3.Recuperación_HundirLaFlota;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Barco {
    private TipoBarco tipo;
    private int tamano;
    private int vida;
    private List<Posicion> posiciones;

    public Barco(TipoBarco tipo) {
        this.tipo = tipo;
        switch (tipo) {
            case PORTAAVIONES -> tamano = 5;
            case ACORAZADO -> tamano = 4;
            case SUBMARINO -> tamano = 3;
            case DESTRUCTOR -> tamano = 2;
            case FRAGATA -> tamano = 1;
        }
        vida = tamano;
        posiciones = new ArrayList<>();
    }

    public void addPosicion(Posicion p) {
        posiciones.add(p);
    }

    public boolean isHundido() {
        if (vida == 0) {
            return true;
        }
        return false;
    }

    private boolean isInPosicion(Posicion p) {
        if (posiciones.contains(p)) {
            return true;
        }
        return false;
    }

    public Resultado checkImpacto(Posicion p) {
        if (isInPosicion(p)) {
            vida--;
            if (vida == 0)
                return Resultado.HUNDIDO;
            else
                return Resultado.TOCADO;
        }
        return Resultado.AGUA;
    }

    public TipoBarco getTipo() {
        return tipo;
    }

    public void setTipo(TipoBarco tipo) {
        this.tipo = tipo;
    }

    public int getTamano() {
        return tamano;
    }

    public void setTamano(int tamano) {
        this.tamano = tamano;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public Posicion[] getPosiciones() {
        return posiciones.toArray(Posicion[]::new);
    }

    public void setPosiciones(Posicion[] posiciones) {
        this.posiciones = Arrays.stream(posiciones).toList();
    }

    @Override
    public String toString() {
        return "Barco{" +
                "tipo=" + tipo +
                ", tamano=" + tamano +
                ", vida=" + vida +
                ", posiciones=" + posiciones +
                '}';
    }
}
