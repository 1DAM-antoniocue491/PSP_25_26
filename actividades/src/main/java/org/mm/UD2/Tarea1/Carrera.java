package org.mm.UD2.Tarea1;

public class Carrera {
    public static void main(String[] args) {
        OrganizadorCarrera organizador = new OrganizadorCarrera();

        System.out.println("Todos los 4 hilos creados");

        Corredores corredor1 = new Corredores(1, organizador);
        Corredores corredor2 = new Corredores(2, organizador);
        Corredores corredor3 = new Corredores(3, organizador);
        Corredores corredor4 = new Corredores(4, organizador);

        corredor1.start();
        corredor2.start();
        corredor3.start();
        corredor4.start();

        //if (organizador.isCorredorActivo().get())
    }
}
