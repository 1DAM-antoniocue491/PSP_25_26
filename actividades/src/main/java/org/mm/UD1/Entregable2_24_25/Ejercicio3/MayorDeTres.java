package org.mm.UD1.Entregable2_24_25.Ejercicio3;

import static java.lang.Integer.MIN_VALUE;

public class MayorDeTres {
    public static void main(String[] args) {
        int mayor = MIN_VALUE;

        for (String valor : args) {
            int num = Integer.parseInt(valor);
            if (num > mayor) {
                mayor = num;
            }
        }

        System.out.println("El número mayor de " + args[0] + ", " + args[1] + " y " + args[2] + " es: " + mayor);
    }
}
