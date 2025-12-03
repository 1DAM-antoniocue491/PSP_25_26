package org.mm.UD2.Examen25_26.Ejercicio1;

public class Cocinero extends Thread{
    private int id;
    private static int idGeneral = 1;
    private int numPlatos = 0;
    private BarraPasee barra;
    private TipoCocinero tipoCocinero;

    public Cocinero(BarraPasee barra, TipoCocinero tipoCocinero) {
        this.id = idGeneral;
        idGeneral++;
        this.barra = barra;
        this.tipoCocinero = tipoCocinero;
    }

    @Override
    public void run() {
        while (numPlatos <= 25) {
            int tipoPlato = (int) (Math.random() * 2);
            TipoPlato tipo = null;
            switch (tipoPlato) {
                case 0: tipo = TipoPlato.ENTRANTES; break;
                case 1: tipo = TipoPlato.POSTRES; break;
                case 2: tipo = TipoPlato.PRINCIPALES; break;
            }
            int tiempoPreparacion = (int) (Math.random() * 20);
            barra.prepararPlato(id, tipo, tiempoPreparacion);

            int time = 0;
            switch (tipoCocinero) {
                case APRENDIZ -> time = 3500;
                case SOUS_CHEF -> time = 2500;
                case CHEF_SENIOR -> time = 2000;
                case COCINERO_JUNIOR -> time = 3000;
            }

            numPlatos++;

            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        barra.addCocinerTerminado();

        System.out.println("EL COCINERO_" + id + " YA HA REALIZADO LA CANTIDAD DE 25 PLATOS");
    }
}
