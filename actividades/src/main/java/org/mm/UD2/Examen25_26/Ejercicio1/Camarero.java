package org.mm.UD2.Examen25_26.Ejercicio1;

public class Camarero extends Thread {
    private int id;
    private static int idGeneral = 1;
    private BarraPasee barra;

    public Camarero (BarraPasee barra) {
        this.id = idGeneral;
        idGeneral++;
        this.barra = barra;
    }

    @Override
    public void run() {
        boolean terminado = false;
        while (!terminado) {
            barra.servirPlato(id);

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.err.println("Error: " + e.getMessage());
            }

            terminado = barra.checkCocinerosTerminados();
        }

        System.out.println("EL CAMARERO_" + id + " HA TERMINADO SU JORNADA LABORAL");
    }
}
