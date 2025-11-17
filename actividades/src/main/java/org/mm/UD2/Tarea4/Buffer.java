package org.mm.UD2.Tarea4;

import java.rmi.MarshalException;
import java.util.ArrayList;
import java.util.List;

public class Buffer {
    private List<Integer> buffer = new ArrayList<>();
    private int id;

    public synchronized void generar(boolean tipo, int id) {
        while (buffer.size() == 10) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
        }

        int num = 0;

        if (tipo) {
            do {
                num = (int) (Math.random() * 20);
            } while (num%2!=0);
        } else {
            do {
                num = (int) (Math.random() * 20);
            } while (num%2==0);
        }

        buffer.add(num);
        System.out.println("PRODUCTOR_" + id + " a introducido el número " + num + " en el buffer.");

        notifyAll();
    }

    public synchronized void obtener(int id) {
        while (buffer.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
        }

        int num = buffer.get(0);
        buffer.remove(0);

        System.out.println("CONSUMIDOR_" + id + " ha obtenido el número " + num + " del buffer.");

        notifyAll();
    }
}
