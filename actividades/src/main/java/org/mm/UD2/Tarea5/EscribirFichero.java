package org.mm.UD2.Tarea5;

public class EscribirFichero extends Thread{
    private Buffer buffer;

    public EscribirFichero(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }


    }
}
