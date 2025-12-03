package org.mm.UD2.Entregable25_26.Ejercicio2;

import java.util.ArrayList;
import java.util.HashMap;

public class Vehiculo extends Thread {
    private int id;
    private static int idGeneral = 1;
    private Tipo tipo;
    private int cantRepostajes = 0;
    private int cantidad = 0;
    private Deposito deposito;
    private int totalLitros = 0;
    private boolean terminado = true;
    private ArrayList<Boolean> arrayListTerminado = new ArrayList<>();

    public Vehiculo(Deposito deposito, Tipo tipo) {
        this.id = idGeneral;
        this.tipo = tipo;
        idGeneral++;
        this.deposito = deposito;
    }

    @Override
    public void run() {
        String nombre = "VEHICULO_" + id;

        Thread.currentThread().setName(nombre);

        while (terminado) {
            HashMap<String, Integer> vehiculoRegistro = deposito.getRegistro();
            ArrayList<Integer> cantidadRepostajes = new ArrayList<>(vehiculoRegistro.values());

            if (cantidadRepostajes.isEmpty()) {
                arrayListTerminado.add(false);
            } else {
                for (int cant : cantidadRepostajes) {
                    if (cant >= 5) {
                        arrayListTerminado.add(true);
                    } else {
                        arrayListTerminado.add(false);
                    }
                }
            }

            if (!arrayListTerminado.contains(false) && arrayListTerminado.size() == 8) {
                terminado = false;
            }

            int litros = (int) ((Math.random() * 30) + 30);
            deposito.repostarVehiculo(litros);

            cantidad = deposito.getCantidadRepostage(nombre);
            if (cantRepostajes != cantidad) {
                totalLitros += litros;
            }
            cantRepostajes = cantidad;

            int time = (int) ((Math.random() * 2000) + 1000);
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }

        System.out.println();
        System.out.println(nombre + ":");
        System.out.println("Ha repostado " + totalLitros + " litros en total.");
        System.out.println("Ha repostado una cantidad de " + cantRepostajes + " veces.");
    }
}
