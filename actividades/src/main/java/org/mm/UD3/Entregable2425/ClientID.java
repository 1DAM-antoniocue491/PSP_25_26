package org.mm.UD3.Entregable2425;

public class ClientID {
    private static int idGeneral = 0;
    private int id;
    private int idPartida;
    private ColorFicha colorFicha;

    public ClientID(int idPartida, ColorFicha colorFicha) {
        id = idGeneral;
        idGeneral++;
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
