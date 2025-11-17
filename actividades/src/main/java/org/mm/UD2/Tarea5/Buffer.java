package org.mm.UD2.Tarea5;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Buffer {
    private ArrayList<Integer> buffer = new ArrayList<>();

    public synchronized void generar() {
        while (buffer.size() >= 1000) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.err.println("Error: " + e.getMessage());
        }

        ArrayList<Integer> numeros = new ArrayList<>();
        for (int x = 0; x < 100; x++) {
            int num = (int) (Math.random() * 20);
            numeros.add(num);
        }

        buffer.addAll(numeros);

        notifyAll();
    }

    public synchronized
}
