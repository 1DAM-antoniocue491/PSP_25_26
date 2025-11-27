package org.mm.UD2.Entregable25_26.Ejercicio1;

public class Panadero extends Thread {
    private int id;
    private Almacen almacen;
    private Velocidad velocidad;
    private int time;
    private int cantidadBarras = 0;

    public Panadero(Almacen almacen, int id, Velocidad velocidad) {
        this.almacen = almacen;
        this.id = id;
        this.velocidad = velocidad;
    }

    @Override
    public void run() {
        Thread.currentThread().setName("PANADERO_" + id);

        switch (velocidad) {
            case ALTA -> time = 2000;
            case MEDIA -> time = 1500;
            case BAJA -> time = 1000;
        }

        while (cantidadBarras < 40) {
            almacen.hornearPan(id);
            cantidadBarras++;
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }

        switch (id) {
            case 1 -> almacen.putAcabado(0);
            case 2 -> almacen.putAcabado(1);
            case 3 -> almacen.putAcabado(2);
        }
    }
}
