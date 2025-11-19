package org.mm.UD2.Tarea6;

public class Media extends Thread {
    private BufferMedicionLugar buffer;

    public Media(BufferMedicionLugar buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true) {
            buffer.obtenerMedida();
        }
    }
}
