package org.mm.UD2.Tarea0;

import java.util.concurrent.atomic.AtomicBoolean;

public class BufferLetra {
    private char character = ' ';
    private AtomicBoolean disponible = new AtomicBoolean(false);

    public synchronized void dejarLetra(char character){
        while (disponible.get()) {
            try {
                wait();
            } catch (InterruptedException e) { }
        }
        this.character = character;
        disponible.set(true);
        notify();
    }

    public synchronized char cogerLetra() {
        while (!disponible.get()) {
            try {
                wait();
            } catch (InterruptedException e) { }
        }
        disponible.set(false);
        notify();
        return character;
    }
}
