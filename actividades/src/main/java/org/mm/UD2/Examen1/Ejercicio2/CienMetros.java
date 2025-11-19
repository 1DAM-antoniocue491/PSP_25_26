package org.mm.UD2.Examen1.Ejercicio2;

import java.util.ArrayList;
import java.util.Arrays;

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

        try {
            atletas[0].join();
            atletas[1].join();
            atletas[2].join();
            atletas[3].join();
            atletas[4].join();
        } catch (InterruptedException e) {
            System.err.println("Error: " + e.getMessage());
        }

        System.out.println();

        for (Atleta atleta : atletasLesionados((Atleta[]) atletas)) {
            System.out.println("Atleta " + atleta.getNombre() + " ha sufrido una lesión. Queda descalificado de la carrera.");
        }
        System.out.println("El ganador de la carrera es: " + fotoFinish((Atleta[]) atletas).getName());
        System.out.println("Ohhhh, la carrera ha acabado. Gracias por venir.");
    }

    private static Atleta fotoFinish(Atleta[] atletas) {
        Atleta atleta = atletas[0];
        for (Atleta atletaIndividual : atletas) {
            if (atleta.getResultado() < atletaIndividual.getResultado() && !atletaIndividual.isLesionado()) {
                atleta  = atletaIndividual;
            }
        }
        return atleta;
    }

    private static Atleta[] atletasLesionados(Atleta[] atletas) {
        ArrayList<Atleta> atletaArrayList = new ArrayList<>();
        for (Atleta atleta : atletas) {
            if (atleta.isLesionado()) {
                atletaArrayList.add(atleta);
            }
        }
        return atletaArrayList.toArray(Atleta[]::new);
    }
}
