package org.mm.UD2.Entregable25_26.Ejercicio2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class CamionCisterna extends Thread {
    private Deposito deposito;
    private int id;
    private static int idGeneral = 1;
    private int cantRepostajes = 0;
    private boolean terminado = true;
    private ArrayList<Boolean> arrayListTerminado = new ArrayList<>();

    public CamionCisterna(Deposito deposito) {
        this.deposito = deposito;
        this.id = idGeneral;
        idGeneral++;
    }

    @Override
    public void run() {
        while (terminado) {
            HashMap<String, Integer> vehiculoRegistro = deposito.getRegistro();
            ArrayList<Integer> cantidadRepostajes = new ArrayList<>(vehiculoRegistro.values());

            if (cantidadRepostajes.isEmpty()) {
                arrayListTerminado.add(true);
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

            System.out.println("Cuidado. " + Thread.currentThread().getName() + " está repostando.");

            deposito.rellenarDeposito();
            cantRepostajes++;
        }

        System.out.println();
        System.out.println("Camión cisterna con ID_" + id);
        System.out.println("Ha repostado " + cantRepostajes + " veces.");
    }
}
