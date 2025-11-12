package org.mm.UD2.Tarea3;

import java.util.ArrayList;

public class GestorTrabajo {
    public static void main(String[] args) throws InterruptedException {
        Trabajo trabajo = new Trabajo(new ArrayList<>(), 50, 30);

        Thread jefe1 = new Jefe(trabajo, 1);
        Thread jefe2 = new Jefe(trabajo, 2);
        Thread jefe3 = new Jefe(trabajo, 3);

        Thread trabajador1 = new Trabajador(trabajo, 1);
        Thread trabajador2 = new Trabajador(trabajo, 2);
        Thread trabajador3 = new Trabajador(trabajo, 3);

        jefe1.start();
        jefe2.start();
        jefe3.start();

        trabajador1.start();
        trabajador2.start();
        trabajador3.start();

        try {
            jefe1.join();
            jefe2.join();
            jefe3.join();

            trabajador1.join();
            trabajador2.join();
            trabajador3.join();
        } catch (InterruptedException e) {
            System.err.println("Error: " + e.getMessage());
        }

        System.out.println();

        if (trabajo.getTareasPendientes() >= trabajo.getPicoMaxTrabajo()) {
            System.out.println("La empresa ha quebrado");
        } else if (trabajo.getTotalTareasRealizadas() == trabajo.getObjetivo()) {
            System.out.println("OBJETIVO CUMLIDO: La empresa ha realizado un total de " + trabajo.getTotalTareasRealizadas() + " tareas");
        }

        System.out.println();

        System.out.println("Total de trabajos realizados: " + trabajo.getTotalTareasRealizadas());
        System.out.println("Tareas pendientes por realizar: " + trabajo.getTareasPendientes());
        System.out.println("Pico máximo de trabajo: " + trabajo.getPicoMaxTrabajo());
    }
}
