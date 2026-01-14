package org.mm.UD3.Entregable2425;

import java.io.Serializable;

public class ClientID implements Serializable {
    private static int idGeneral = 0;
    private int id;
    private int idPartida;
    private ColorFicha colorFicha;

    public ClientID() {
        id = idGeneral;
        idGeneral++;
    }

    public ClientID(int idPartida, ColorFicha colorFicha) {
        this();
        this.idPartida = idPartida;
        this.colorFicha = colorFicha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(int idPartida) {
        this.idPartida = idPartida;
    }

    public ColorFicha getColorFicha() {
        return colorFicha;
    }

    public void setColorFicha(ColorFicha colorFicha) {
        this.colorFicha = colorFicha;
    }

    @Override
    public String toString() {
        return "ClientID{" +
                "id=" + id +
                ", idPartida=" + idPartida +
                ", colorFicha=" + colorFicha +
                '}';
    }
}
