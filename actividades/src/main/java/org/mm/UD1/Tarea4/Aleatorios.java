package org.mm.UD1.Tarea4;

import org.mm.UD1.Funciones.ProcesoJava;
import org.mm.UD1.Tarea3.LanzadorSumador;

import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Aleatorios {
    private static final Logger logger = Logger.getLogger(LanzadorSumador.class.getName());

    public static void main(String[] args) {
        ProcesoJava procesoJava = new ProcesoJava();
        Scanner teclado = new Scanner(System.in);

        String opcion = "";
        do {
            try {
                opcion = "";

                Process p = procesoJava.exec(GeneradorAleatorio.class);
                int val1 = p.waitFor();

                int aleatorio = aleatorio(0, 10);

                if (aleatorio == val1)
                    System.out.println("Los dos valores generados son iguales");
                else
                    System.out.println("Los dos valores generados no son iguales");

                System.out.print("¿Quieres siguir generando aleatorios (s/n)? ");
                opcion = teclado.next();
            } catch (Exception e) {
                logger.log(Level.SEVERE, e.getMessage());
            }
        } while (opcion.equals("s"));
    }

    public static int aleatorio (int min, int max)  {
        Random r = new Random();
        return min + r.nextInt(max - min + 1);  // Entre min y max -> [min, max];
    }
}
