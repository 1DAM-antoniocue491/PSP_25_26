package org.mm.UD2.Examen25_26.Ejercicio2;

public class Vehiculo extends Thread {
    private int id;
    private static int idGeneral = 1;
    private TipoVehiculo tipoVehiculo;
    private int tiempoPago;
    private GestorPeaje gestorPeaje;
    private boolean pagado = false;
    private BarreraSalida barreraSalida = new BarreraSalida();

    public Vehiculo(TipoVehiculo tipoVehiculo, GestorPeaje gestorPeaje) {
        this.id = idGeneral;
        idGeneral++;
        this.tipoVehiculo = tipoVehiculo;
        this.gestorPeaje = gestorPeaje;
        switch (tipoVehiculo) {
            case MOTO -> tiempoPago = 500;
            case COCHE -> tiempoPago = 1000;
            case CAMION -> tiempoPago = 2000;
        }
    }

    @Override
    public void run() {
        System.out.println("VEHÍCULO_" + id + " de TIPO " + tipoVehiculo + " llegando al peaje ...");
        gestorPeaje.pasarVehiculo(this);

        boolean terminado = false;

        while (!terminado) {
            terminado = barreraSalida.terminado(this);
        }

        System.out.println("VEHÍCULO_" + id + " de TIPO " + tipoVehiculo + " ha cruzado la barrera de salida.");
    }

    @Override
    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TipoVehiculo getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public int getTiempoPago() {
        return tiempoPago;
    }

    public void setTiempoPago(int tiempoPago) {
        this.tiempoPago = tiempoPago;
    }

    public GestorPeaje getGestorPeaje() {
        return gestorPeaje;
    }

    public void setGestorPeaje(GestorPeaje gestorPeaje) {
        this.gestorPeaje = gestorPeaje;
    }

    public boolean isPagado() {
        return pagado;
    }

    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }
}
