package org.mm.UD2.Entregable25_26.Ejercicio1;

public class Repartidor extends Thread {
    private Almacen almacen;
    private boolean terminar = true;
    private int id;
    private static int idGeneral = 1;

    public Repartidor(Almacen almacen) {
        this.almacen = almacen;
        this.id = idGeneral;
        idGeneral++;
    }

    @Override
    public void run() {
        Thread.currentThread().setName("REPARTIDOR_" + id);

        for (boolean panadero : almacen.getAcabados()) {
            if (!panadero) {
                terminar = false;
            }
        }

        while (!terminar) {
            almacen.recogerPan();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }
}
