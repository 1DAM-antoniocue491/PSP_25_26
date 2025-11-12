package org.mm.UD2.Tarea3;

public class Jefe extends Thread {
    private Trabajo trabajo;
    private int idJefe;

    public Jefe(Trabajo trabajo, int idJefe) {
        this.trabajo = trabajo;
        this.idJefe = idJefe;
    }

    @Override
    public void run() {
        while (trabajo.getEnEjecucion().get()) {
            trabajo.addTarea();
            System.out.println("JEFE_" + idJefe + ", ha añadido la tarea 'tarea" + trabajo.getIdTarea().get() + "' de JEFE_" + idJefe + " Hay un total de " + trabajo.getTareasPendientes() + " pendientes");
        }
        System.out.println("JEFE_" + idJefe + " Terminado. Tareas realizadas: " + trabajo.getTotalTareasRealizadas() + "; Tareas pendientes: " + trabajo.getTareasPendientes() + "; Máximo pico de trabajo acumulado: " + trabajo.getPicoMaxTrabajo());
    }
}
