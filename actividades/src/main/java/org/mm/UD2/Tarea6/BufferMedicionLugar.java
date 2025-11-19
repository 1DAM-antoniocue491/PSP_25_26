package org.mm.UD2.Tarea6;

import java.util.ArrayList;

public class BufferMedicionLugar {
    private String lugar;
    private float buffer = 0f;
    private ArrayList<Float> arrayMediciones = new ArrayList<>();

    public BufferMedicionLugar(String lugar) {
        this.lugar = lugar;
    }

    public synchronized void ponerMedia(float medida) {
        while (buffer != 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }

        buffer = medida;

        notifyAll();
    }

    public synchronized void obtenerMedida() {
        while (buffer == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }

        arrayMediciones.add(buffer);

        System.out.println("MEDIDOR MIDE: " + buffer);
        System.out.println("MEDICIÓN OBTENIDA: " + buffer);
        System.out.println("NÚMERO DE MEDICIONES REALIZADAS: " + arrayMediciones.size());
        System.out.println("MEDIA OBTENIDA: " + getMedia());
        System.out.println("*************************************");

        buffer = 0f;

        notifyAll();
    }

    private float getMedia() {
        float suma = 0f;
        for (float medicion : arrayMediciones) {
            suma += medicion;
        }
        return suma / arrayMediciones.size();
    }
}
