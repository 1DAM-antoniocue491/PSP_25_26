package org.mm.UD2.Tarea3;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Trabajo {
    private ArrayList<String> tareasPendientes;
    private int trabajoMaximo;
    private int objetivo;
    private int picoMaxTrabajo = 0;
    private int totalTareasRealizadas = 0;
    private AtomicBoolean enEjecucion = new AtomicBoolean(true);
    private AtomicBoolean trabajoTerminado = new AtomicBoolean(false);
    private AtomicInteger idTarea = new AtomicInteger(1);

    public Trabajo(ArrayList<String> tareasPendientes, int trabajoMaximo, int objetivo) {
        this.tareasPendientes = tareasPendientes;
        this.trabajoMaximo = trabajoMaximo;
        this.objetivo = objetivo;
    }

    public synchronized void addTarea() {
        while (tareasPendientes.size() == trabajoMaximo) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        tareasPendientes.add("tarea" + idTarea.get());

        if (tareasPendientes.size() > picoMaxTrabajo) {
            picoMaxTrabajo = tareasPendientes.size();
        }

        if (getTareasPendientes() >= trabajoMaximo || getTotalTareasRealizadas() == objetivo) {
            enEjecucion.set(false);
            trabajoTerminado.set(true);
        }

        idTarea.set(idTarea.get() + 1);

        notifyAll();
    }

    public synchronized String getTarea() {
        while (tareasPendientes.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        String value = tareasPendientes.get(0);
        tareasPendientes.remove(0);

        totalTareasRealizadas++;

        if (getTotalTareasRealizadas() == getObjetivo()) {
            trabajoTerminado.set(true);
        }

        notifyAll();
        return value;
    }

    public AtomicBoolean getEnEjecucion() {
        return enEjecucion;
    }

    public AtomicBoolean getTrabajoTerminado() {
        return trabajoTerminado;
    }

    public int getTotalTareasRealizadas() {
        return totalTareasRealizadas;
    }

    public int getPicoMaxTrabajo() {
        return picoMaxTrabajo;
    }

    public int getTareasPendientes() {
        return tareasPendientes.size();
    }

    public int getObjetivo() {
        return objetivo;
    }

    public AtomicInteger getIdTarea() {
        return idTarea;
    }
}
