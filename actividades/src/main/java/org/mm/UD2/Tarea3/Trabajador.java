package org.mm.UD2.Tarea3;

public class Trabajador extends Thread {
    private Trabajo trabajo;
    private int idTrabajador;

    public Trabajador(Trabajo trabajo, int idTrabajador) {
        this.trabajo = trabajo;
        this.idTrabajador = idTrabajador;
    }

    @Override
    public void run() {
        while (trabajo.getEnEjecucion().get()) {
            while (!trabajo.getTrabajoTerminado().get()) {
                String tarea = trabajo.getTarea();
                System.out.println("TRABAJADOR_" + idTrabajador + " ha realizado la tarea '" + tarea + "' Quedan ahora " + trabajo.getTareasPendientes() + " pendientes");
            }
            System.out.println("TRABAJADOR_" + idTrabajador + " ha terminado su trabajo");
        }
    }
}
