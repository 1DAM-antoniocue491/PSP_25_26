package org.mm.UD2.Entregable25_26.Ejercicio1;

public class BarraPan {
    private int id;
    private static int idGeneral = 0;
    private int gramos;
    private int idPanadero;

    public BarraPan(int gramos, int idPanadero) {
        this.id = idGeneral;
        this.gramos = gramos;
        this.idPanadero = idPanadero;
        idGeneral++;
    }

    public int getId() {
        return id;
    }

    public int getGramos() {
        return gramos;
    }

    public int getIdPanadero() {
        return idPanadero;
    }
}
