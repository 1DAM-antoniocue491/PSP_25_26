package org.mm.UD2.Tarea6;

public class Main {
    public static void main(String[] args) {
        BufferMedicionLugar buffer = new BufferMedicionLugar("Sevilla");

        Thread medidor = new Medidor(buffer);
        Thread media = new Media(buffer);

        medidor.start();
        media.start();
    }
}
