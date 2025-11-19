package org.mm.UD2.Tarea5;

public class GeneradorNumeros extends Thread {
    private Buffer buffer;
    private int id;

    public GeneradorNumeros(Buffer buffer, int id) {
        this.buffer = buffer;
        this.id = id;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.err.println("Error: " + e.getMessage());
            }

            buffer.generar(id);
        }
    }
}
