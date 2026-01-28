package org.mm.UD3.Recuperación_HundirLaFlota;

import java.util.Arrays;

public class RespuestaJugada {
    private Resultado resultado;
    private boolean turno;
    private String[][] tableroOponente;

    public RespuestaJugada(Resultado resultado, boolean turno, String[][] tableroOponente) {
        this.resultado = resultado;
        this.turno = turno;
        this.tableroOponente = tableroOponente;
    }

    public Resultado getResultado() {
        return resultado;
    }

    public void setResultado(Resultado resultado) {
        this.resultado = resultado;
    }

    public boolean isTurno() {
        return turno;
    }

    public void setTurno(boolean turno) {
        this.turno = turno;
    }

    public String[][] getTableroOponente() {
        return tableroOponente;
    }

    public void setTableroOponente(String[][] tableroOponente) {
        this.tableroOponente = tableroOponente;
    }

    @Override
    public String toString() {
        return "RespuestaJugada{" +
                "resultado=" + resultado +
                ", turno=" + turno +
                ", tableroOponente=" + Arrays.toString(tableroOponente) +
                '}';
    }
}
