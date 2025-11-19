package org.mm.UD2.Tarea5;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Buffer {
    private ArrayList<Integer> buffer = new ArrayList<>();

    public synchronized void generar(int id) {
        while (buffer.size() > 1000) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }

        ArrayList<Integer> numeros = new ArrayList<>();
        for (int x = 0; x < 100; x++) {
            int num = (int) (Math.random() * 20);
            numeros.add(num);
        }

        buffer.addAll(numeros);

        System.out.println("El Hilo_" + id + " ha añadido números al buffer. Tamaño actual del buffer: " + buffer.size());

        notifyAll();
    }

    public synchronized void escribirFichero(String path) {
        while (buffer.size() < 1000) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }

        File f = new File(path);

        try {
            // Existencia y tratamiento del fichero
            if (!f.exists()) {
                f.createNewFile();
            }

            FileWriter fichero = new FileWriter(f, true);
            BufferedWriter bw = new BufferedWriter(fichero);
            for (int n : buffer) {
                bw.write(n);
            }
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        buffer.clear();

        System.out.println("El buffer se acaba de vaciar.");
        notifyAll();
    }
}
