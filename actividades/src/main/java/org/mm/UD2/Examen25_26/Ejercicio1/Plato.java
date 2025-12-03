package org.mm.UD2.Examen25_26.Ejercicio1;

public class Plato {
    private int id;
    private static int idGeneral = 1;
    private TipoPlato tipo;
    private int tiempoPreparacion;
    private int idCocinero;

    public Plato(TipoPlato tipo, int tiempoPreparacion, int idCocinero) {
        this.id = idGeneral;
        idGeneral++;
        this.tipo = tipo;
        this.tiempoPreparacion = tiempoPreparacion;
        this.idCocinero = idCocinero;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TipoPlato getTipo() {
        return tipo;
    }

    public void setTipo(TipoPlato tipo) {
        this.tipo = tipo;
    }

    public int getTiempoPreparacion() {
        return tiempoPreparacion;
    }

    public void setTiempoPreparacion(int tiempoPreparacion) {
        this.tiempoPreparacion = tiempoPreparacion;
    }

    public int getIdCocinero() {
        return idCocinero;
    }

    public void setIdCocinero(int idCocinero) {
        this.idCocinero = idCocinero;
    }
}
