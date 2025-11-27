package org.mm.UD2.Entregable25_26.Ejercicio2;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Deposito deposito = new Deposito();

        Thread vehiculo1 = new Vehiculo(deposito, Tipo.COCHE);
        Thread vehiculo2 = new Vehiculo(deposito, Tipo.MOTO);
        Thread vehiculo3 = new Vehiculo(deposito, Tipo.FURGONETA);
        Thread vehiculo4 = new Vehiculo(deposito, Tipo.COCHE);
        Thread vehiculo5 = new Vehiculo(deposito, Tipo.MOTO);
        Thread vehiculo6 = new Vehiculo(deposito, Tipo.FURGONETA);
        Thread vehiculo7 = new Vehiculo(deposito, Tipo.COCHE);
        Thread vehiculo8 = new Vehiculo(deposito, Tipo.MOTO);

        Thread camion1 = new CamionCisterna(deposito);
        Thread camion2 = new CamionCisterna(deposito);

        vehiculo1.start();
        vehiculo2.start();
        vehiculo3.start();
        vehiculo4.start();
        vehiculo5.start();
        vehiculo6.start();
        vehiculo7.start();
        vehiculo8.start();

        camion1.start();
        camion2.start();
    }
}
