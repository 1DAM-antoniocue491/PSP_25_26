package org.mm.UD2.Examen25_26.Ejercicio2;

public class Main {
    public static void main(String[] args) {
        GestorPeaje gestorPeaje = new GestorPeaje();

        Thread vehiculo1 = new Vehiculo(TipoVehiculo.COCHE, gestorPeaje);
        Thread vehiculo2 = new Vehiculo(TipoVehiculo.CAMION, gestorPeaje);
        Thread vehiculo3 = new Vehiculo(TipoVehiculo.MOTO, gestorPeaje);
        Thread vehiculo4 = new Vehiculo(TipoVehiculo.MOTO, gestorPeaje);
        Thread vehiculo5 = new Vehiculo(TipoVehiculo.COCHE, gestorPeaje);
        Thread vehiculo6 = new Vehiculo(TipoVehiculo.CAMION, gestorPeaje);
        Thread vehiculo7 = new Vehiculo(TipoVehiculo.CAMION, gestorPeaje);
        Thread vehiculo8 = new Vehiculo(TipoVehiculo.MOTO, gestorPeaje);
        Thread vehiculo9 = new Vehiculo(TipoVehiculo.CAMION, gestorPeaje);
        Thread vehiculo10 = new Vehiculo(TipoVehiculo.MOTO, gestorPeaje);

        vehiculo1.start();
        vehiculo2.start();
        vehiculo3.start();
        vehiculo4.start();
        vehiculo5.start();
        vehiculo6.start();
        vehiculo7.start();
        vehiculo8.start();
        vehiculo9.start();
        vehiculo10.start();

        try {
            vehiculo1.join();
            vehiculo2.join();
            vehiculo3.join();
            vehiculo4.join();
            vehiculo5.join();
            vehiculo6.join();
            vehiculo7.join();
            vehiculo8.join();
            vehiculo9.join();
            vehiculo10.join();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

        System.out.println();
        System.out.println(gestorPeaje.getInforme());
    }
}
