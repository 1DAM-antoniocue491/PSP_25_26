package org.mm.UD2.Examen25_26.Ejercicio1;

import java.util.ArrayList;

public class BarraPasee {
    ArrayList<Plato> pass = new ArrayList<>();
    private static int idTanda = 1;
    ArrayList<Boolean> cocinerosTerminados = new ArrayList<>();

    public synchronized void servirPlato(int idCamarero) {
        if (checkCocinerosTerminados()) {
            return;
        }

        while (pass.size() < 4) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }

        ArrayList<Plato> tanda = new ArrayList<>();
        tanda.add(pass.get(0));
        tanda.add(pass.get(1));
        tanda.add(pass.get(2));
        tanda.add(pass.get(3));

        for (int i = 0; i < 4; i++) {
            pass.remove(0);
        }

        System.out.println("TANDA_" + idTanda);
        System.out.println("Camarero que ha recogido esta tanda: CAMARERO_" + idCamarero);
        System.out.println("PLATOS QUE CONTIENE: ");
        for (Plato plato: tanda) {
            System.out.println("    Plato_" + plato.getId() + " es de tipo " + plato.getTipo());
        }
        System.out.println();
        idTanda++;

        notifyAll();
    }

    public synchronized void prepararPlato(int idCocinero, TipoPlato tipoPlato, int tiempoPreparacion) {
        while (pass.size() == 12) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }

        Plato plato = new Plato(tipoPlato, tiempoPreparacion, idCocinero);
        pass.add(plato);

        notifyAll();
    }

    public synchronized void addCocinerTerminado() {
        cocinerosTerminados.add(true);

        notifyAll();
    }

    public synchronized boolean checkCocinerosTerminados() {
        if (cocinerosTerminados.size() == 4) {
            return true;
        }

        notifyAll();
        return false;
    }
}
