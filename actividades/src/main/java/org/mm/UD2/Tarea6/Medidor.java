package org.mm.UD2.Tarea6;

public class Medidor extends Thread {
    private BufferMedicionLugar buffer;

    public Medidor(BufferMedicionLugar buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.err.println("Error: " + e.getMessage());
            }

            float medicion = (float) ((Math.random() * (30 - 20 + 1)) + 20);

            buffer.ponerMedia(Math.round(medicion));
        }
    }
}
