package org.mm.UD2.Examen25_26.Ejercicio2;

import java.util.concurrent.atomic.AtomicInteger;

public class CabinaPeaje {
    private int id;
    private static int idGeneral = 1;
    private Vehiculo vehiculo = null;
    private AtomicInteger numVehiculosAtendidos = new AtomicInteger(0);
    private BarreraSalida barreraSalida = new BarreraSalida();

    public CabinaPeaje() {
        this.id = idGeneral;
        idGeneral++;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
        System.out.println("El VEHÍCULO_" + vehiculo.getId() + " ha sido asignado a la CABINA_" + id);
        numVehiculosAtendidos.set(numVehiculosAtendidos.get()+1);
        try {
            Thread.sleep(vehiculo.getTiempoPago());
        } catch (InterruptedException e) {
            System.err.println("Error: " + e.getMessage());
        }
        System.out.println("VEHÍCULO_" + vehiculo.getId() + " de TIPO " + vehiculo.getTipoVehiculo() + " ha terminado de pagar el peaje.");
        vehiculo.setPagado(true);
        if (barreraSalida.terminado(vehiculo)) {
            this.vehiculo = null;
        }
    }

    public boolean espacioLibre() {
        if (vehiculo != null) {
            return false;
        }

        return true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Thread getVehiculo() {
        return vehiculo;
    }

    public AtomicInteger getNumVehiculosAtendidos() {
        return numVehiculosAtendidos;
    }

    public void setNumVehiculosAtendidos(AtomicInteger numVehiculosAtendidos) {
        this.numVehiculosAtendidos = numVehiculosAtendidos;
    }
}
