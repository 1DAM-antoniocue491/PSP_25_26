package org.mm.UD2.Entregable25_26.Ejercicio1;

public class Main {
    public static void main(String[] args) {
        Almacen almacen = new Almacen();

        // Creación de los hilos Panadero
        Thread panadero1 = new Panadero(almacen, 1, Velocidad.ALTA);
        Thread panadero2 = new Panadero(almacen, 2, Velocidad.MEDIA);
        Thread panadero3 = new Panadero(almacen, 3, Velocidad.BAJA);

        // Creación de los hilos Repartidor
        Thread repartidor1 = new Repartidor(almacen);
        Thread repartidor2 = new Repartidor(almacen);

        // Inicio de los hilos Panadero
        panadero1.start();
        panadero2.start();
        panadero3.start();

        // Inicio de los hilos Repartidor
        repartidor1.start();
        repartidor2.start();
    }
}
