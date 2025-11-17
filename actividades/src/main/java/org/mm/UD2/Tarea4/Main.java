package org.mm.UD2.Tarea4;

public class Main {
    public static void main(String[] args) {
        Buffer buffer = new Buffer();

        Thread productor1 = new Productor(1, buffer, true);
        Thread productor2 = new Productor(2, buffer, false);

        Thread consumidor1 = new Consumidor(3, buffer);

        productor1.start();
        productor2.start();

        consumidor1.start();
    }
}
