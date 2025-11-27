package org.mm.UD2.Entregable25_26.Ejercicio2;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Deposito {
    private AtomicInteger deposito = new AtomicInteger(1000);
    private HashMap<String, Integer> registro = new HashMap<>();
    private int contador = 0;

    public synchronized void rellenarDeposito() {
        while (deposito.get() > 300) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }

        deposito.set(deposito.get() + 200);

        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            System.err.println("Error: " + e.getMessage());
        }



        System.out.println("El depósito actualmente tiene " + deposito + " litros. camion");

        notifyAll();
    }

    public synchronized void repostarVehiculo(int litros) {
        while ((deposito.get()-litros) <= 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }

        deposito.set(deposito.get() - litros);

        String nombre = Thread.currentThread().getName();

        if (registro.containsKey(nombre)) {
            int cant = registro.get(nombre);
            cant++;
            registro.put(nombre, cant);
        } else {
            registro.put(nombre, 1);
        }

        System.out.println("El depósito actualmente tiene " + deposito + " litros. vehiculo");

        notifyAll();
    }

    public synchronized int getCantidadRepostage(String nombre) {
        if (registro.containsKey(nombre)) {
            return registro.get(nombre);
        }
        return 0;
    }

    public synchronized HashMap<String, Integer> getRegistro() {
        return registro;
    }
}
