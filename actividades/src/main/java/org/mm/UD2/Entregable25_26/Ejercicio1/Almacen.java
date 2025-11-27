package org.mm.UD2.Entregable25_26.Ejercicio1;

import java.util.ArrayList;
import java.util.Random;

public class Almacen {
    private ArrayList<BarraPan> almacen = new ArrayList<>();
    private static int idTanda = 0;
    private boolean[] acabados = {false, false, false};

    public synchronized void hornearPan(int idPanadero) {

        while (almacen.size() > 15) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }

        int gramos = (int) ((Math.random() * 101) + 200);

        BarraPan barra = new BarraPan(gramos, idPanadero);
        almacen.add(barra);

        System.out.println();
        System.out.println("El " + Thread.currentThread().getName() + " ha añadido una barra con id " + barra.getId() + " y con un peso de " + barra.getGramos() + " gramos.");

        notifyAll();
    }

    public synchronized void recogerPan () {
        while (almacen.size() < 5) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }

        ArrayList<BarraPan> barrasRecogidas = new ArrayList<>();
        for (int x = 0; x < 5; x++) {
            BarraPan barra = almacen.get(0);
            barrasRecogidas.add(barra);
            almacen.remove(0);
        }

        System.out.println();
        System.out.println("El repartidor " + Thread.currentThread().getName() + " ha recogido la tanda de barras con id " + idTanda + ":");
        for (BarraPan barrita : barrasRecogidas) {
            System.out.println("ID de barra: " + barrita.getId());
            System.out.println("    Peso de la barra: " + barrita.getGramos());
            System.out.println("    ID del panadero que la ha orneado: " + barrita.getIdPanadero());
        }
        idTanda++;

        notifyAll();
    }

    public synchronized boolean[] getAcabados() {
        return acabados;
    }

    public synchronized void putAcabado(int posicion) {
        acabados[posicion] = true;
    }
}
