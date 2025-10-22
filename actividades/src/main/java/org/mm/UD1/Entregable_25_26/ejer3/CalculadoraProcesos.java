package org.mm.UD1.Entregable_25_26.ejer3;

import org.mm.UD1.Funciones.ProcesoJava;
import org.mm.UD1.Tarea3.LanzadorSumador;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CalculadoraProcesos {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        ProcesoJava procesoJava = new ProcesoJava();
        Logger logger = Logger.getLogger(CalculadoraProcesos.class.getName());

        char continuar = 'N';
        do {
            System.out.print("Indica un número entero: ");
            int n1 = teclado.nextInt();
            System.out.print("Indica un operador (+, -, /, *): ");
            char operator = teclado.next().charAt(0);
            System.out.print("Indica el segundo numero entero: ");
            int n2 = teclado.nextInt();

            String result = "";

            try {
                Process p = procesoJava.exec(Operador.class, new String[] {String.valueOf(n1), "'" + operator + "'", String.valueOf(n2)});
                result = procesoJava.getSalidaProceso(p);
            } catch (IOException e) {
                logger.log(Level.SEVERE, e.getMessage());
            }

            System.out.println(n1 + " " + operator + " " + n2 + " = " + result);

            System.out.print("Quieres continuar (S/N)? ");
            continuar = teclado.next().charAt(0);
        } while (continuar == 'S');
    }
}
