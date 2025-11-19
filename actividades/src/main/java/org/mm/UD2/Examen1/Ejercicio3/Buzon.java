package org.mm.UD2.Examen1.Ejercicio3;

public class Buzon {
    private Object buffer = "";

    public synchronized void escribir (Object msg) {
        while (buffer != "") {
            try {
                wait();
            } catch (InterruptedException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }

        buffer = msg;

        notifyAll();
    }

    public synchronized Object leer() {
        while (buffer == "") {
            try {
                wait();
            } catch (InterruptedException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }

        Object obj = buffer;
        buffer = "";

        notifyAll();

        return obj;
    }
}
