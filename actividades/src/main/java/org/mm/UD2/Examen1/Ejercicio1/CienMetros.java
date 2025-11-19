package org.mm.UD2.Examen1.Ejercicio1;

public class CienMetros {
    public static void main(String[] args) {
        Thread.currentThread().setName("Carrera Legendaria");

        Thread[] atletas = new Atleta[5];
        atletas[0] = new Atleta(1, "Usain Bolt");
        atletas[1] = new Atleta(2, "Tyson Gay");
        atletas[2] = new Atleta(3, "Carl Lewis");
        atletas[3] = new Atleta(4, "Asafa Powell");
        atletas[4] = new Atleta(5, "Justin Gutlin");

        System.out.println("Inicio de carrera de 100m de nombre Carrera Legendaria");

        String[] ordenes = {"Preparados", "Listos", "Ya"};
        for (String orden : ordenes) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.err.println("Error: " + e.getMessage());
            }

            System.out.println(orden);
            if (orden.equals("Ya")) {
                for (Thread atleta : atletas) {
                    atleta.start();
                }
            }
        }
    }
}
