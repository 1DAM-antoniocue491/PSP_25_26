package org.mm.UD2.Tarea0;

import java.util.TreeMap;

public class Abecedario {
    public static void main(String[] args) {
        BufferLetra bufferLetra = new BufferLetra();

        H H1 = new H(bufferLetra, true);
        H H2 = new H(bufferLetra, false);

        H1.start();
        H2.start();
    }
}

