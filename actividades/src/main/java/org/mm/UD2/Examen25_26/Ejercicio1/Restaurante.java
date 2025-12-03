package org.mm.UD2.Examen25_26.Ejercicio1;

public class Restaurante {
    public static void main(String[] args) {
        BarraPasee barra = new BarraPasee();

        Thread cocinero1 = new Cocinero(barra, TipoCocinero.CHEF_SENIOR);
        Thread cocinero2 = new Cocinero(barra, TipoCocinero.SOUS_CHEF);
        Thread cocinero3 = new Cocinero(barra, TipoCocinero.COCINERO_JUNIOR);
        Thread cocinero4 = new Cocinero(barra, TipoCocinero.APRENDIZ);

        Thread camarero1 = new Camarero(barra);
        Thread camarero2 = new Camarero(barra);
        Thread camarero3 = new Camarero(barra);

        cocinero1.start();
        cocinero2.start();
        cocinero3.start();
        cocinero4.start();

        camarero1.start();
        camarero2.start();
        camarero3.start();
    }
}
