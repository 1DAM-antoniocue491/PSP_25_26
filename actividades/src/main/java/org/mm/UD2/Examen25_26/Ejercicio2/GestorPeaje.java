package org.mm.UD2.Examen25_26.Ejercicio2;

public class GestorPeaje {
    private CabinaPeaje cabina1 = new CabinaPeaje();
    private CabinaPeaje cabina2 = new CabinaPeaje();
    private CabinaPeaje cabina3 = new CabinaPeaje();

    public synchronized void pasarVehiculo(Vehiculo vehiculo) {
        if (cabina1.espacioLibre()) {
            cabina1.setVehiculo(vehiculo);
        } else if (cabina2.espacioLibre()) {
            cabina2.setVehiculo(vehiculo);
        } else if (cabina3.espacioLibre()) {
            cabina3.setVehiculo(vehiculo);
        }
    }

    public String getInforme() {
        return "Cabina 1: " + cabina1.getNumVehiculosAtendidos().get() + "\n" +
                "Cabina 2: " + cabina2.getNumVehiculosAtendidos().get() + "\n" +
                "Cabina 3: " + cabina3.getNumVehiculosAtendidos().get();
    }
}
