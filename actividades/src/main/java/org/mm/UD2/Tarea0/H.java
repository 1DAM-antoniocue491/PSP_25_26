package org.mm.UD2.Tarea0;

import java.util.TreeMap;

public class H extends Thread{
    private BufferLetra bufferLetra;
    private boolean isH1;

    public H(BufferLetra bufferLetra, boolean isH1) {
        this.bufferLetra = bufferLetra;
        this.isH1 = isH1;
    }

    @Override
    public void run() {
        if (isH1) {
            for (int i = 0; i < 10; i++) {
                char character = (char) ('A' + Math.random() * 26);
                bufferLetra.dejarLetra(character);
                try { Thread.sleep(200); } catch (InterruptedException e) { }
            }
        } else {
            for (int i = 0; i < 10; i++) {
                System.out.println("H2 -> " + bufferLetra.cogerLetra());
                try { Thread.sleep(300); } catch (InterruptedException e) { }
            }
        }

    }
}
