package org.mm.UD2.Examen25_26.Ejercicio2;

import java.util.concurrent.atomic.AtomicBoolean;

public class BarreraSalida {

    public synchronized boolean terminado(Vehiculo vehiculo) {
        while (!vehiculo.isPagado()) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            System.err.println("Error: " + e.getMessage());
        }
        notifyAll();
        return true;
    }
}
