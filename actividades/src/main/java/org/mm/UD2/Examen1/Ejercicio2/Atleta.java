package org.mm.UD2.Examen1.Ejercicio2;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Atleta extends Thread {
    private int dorsal;
    private String nombre;
    private LocalTime inicio;
    private double resultado;
    private boolean lesionado = false;

    public Atleta(int dorsal, String nombre) {
        this.dorsal = dorsal;
        this.nombre = nombre;
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalTime getInicio() {
        return inicio;
    }

    public void setInicio(LocalTime inicio) {
        this.inicio = inicio;
    }

    public void setResultado(double resultado) {
        this.resultado = resultado;
    }

    public boolean isLesionado() {
        return lesionado;
    }

    public void setLesionado(boolean lesionado) {
        this.lesionado = lesionado;
    }

    @Override
    public void run() {
        Thread.currentThread().setName(nombre);

        double tiempo = ((Math.random() * (11.5 - 8)) + 8);
        inicio = LocalTime.now();

        boolean terminado = false;

        int num = (int) (Math.random() * 100);

        while (!terminado) {
            terminado = segundosPasados(tiempo);
            if (num <= 30) {
                terminado = true;
                lesionado = true;
            }
        }

        DecimalFormat df = new DecimalFormat("#.0");
        resultado = tiempo;
        try {
            if (terminado && !lesionado) {
                System.out.println("El atleta: " + nombre + " con el dorsal " + dorsal + " ha finalizado la carrera con un tiempo de " + df.parse(String.valueOf(resultado)) + " segundos.");
            } else {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    System.err.println("Error: " + e.getMessage());
                }
                System.out.println(nombre + " grita: Ahhh!!! Me ha dado un tiron");
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean segundosPasados(double second) {
        if ((int) ChronoUnit.SECONDS.between(inicio, LocalTime.now()) >= second) {
            return true;
        }
        return false;
    }

    public double getResultado() {
        return resultado;
    }


}
