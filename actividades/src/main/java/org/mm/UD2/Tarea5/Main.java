package org.mm.UD2.Tarea5;

import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        String ruta = "C:\\Users\\2DAM-acuelop369\\Desktop\\highschool\\2_DAM\\PSP_25_26\\actividades\\src\\main\\java\\org\\mm\\UD2\\Tarea5\\volcado.bin";
        Buffer buffer = new Buffer();

        Thread generadorNumeros1 = new GeneradorNumeros(buffer, 1);
        Thread generadorNumeros2 = new GeneradorNumeros(buffer, 2);
        Thread generadorNumeros3 = new GeneradorNumeros(buffer, 3);

        Thread escribirFichero = new EscribirFichero(buffer, ruta);

        generadorNumeros1.start();
        generadorNumeros2.start();
        generadorNumeros3.start();

        escribirFichero.start();
    }
}
