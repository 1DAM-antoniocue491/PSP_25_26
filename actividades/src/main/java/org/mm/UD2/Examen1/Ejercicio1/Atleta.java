package org.mm.UD2.Examen1.Ejercicio1;

import java.text.DecimalFormat;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Atleta extends Thread {
    private int dorsal;
    private String nombre;
    private LocalTime inicio;

    public Atleta(int dorsal, String nombre) {
        this.dorsal = dorsal;
        this.nombre = nombre;
    }

    @Override
    public void run() {
        Thread.currentThread().setName(nombre);

        double tiempo = ((Math.random() * (11.5 - 8)) + 8);
        inicio = LocalTime.now();

        boolean terminado = false;

        while (!terminado) {
            terminado = segundosPasados(tiempo);
        }

        DecimalFormat df = new DecimalFormat("#.0");
        System.out.println("El atleta: " + nombre + " con el dorsal " + dorsal + " ha finalizado la carrera con un tiempo de " + df.format(tiempo) + " segundos.");
    }

    private boolean segundosPasados(double second) {
        if ((int) ChronoUnit.SECONDS.between(inicio, LocalTime.now()) >= second) {
            return true;
        }
        return false;
    }
}
