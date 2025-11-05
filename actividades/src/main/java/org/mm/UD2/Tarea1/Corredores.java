package org.mm.UD2.Tarea1;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Corredores extends Thread {
    private int hilo;
    private OrganizadorCarrera organizador;


    public Corredores(int hilo, OrganizadorCarrera organizador) {
        this.hilo = hilo;
        this.organizador = organizador;
    }

    @Override
    public void run() {
        organizador.execute(hilo);
    }

}
