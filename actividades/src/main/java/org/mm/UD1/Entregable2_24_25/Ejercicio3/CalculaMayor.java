package org.mm.UD1.Entregable2_24_25.Ejercicio3;

import org.mm.UD1.Entregable2_24_25.Ejercicio2.CountFiles;
import org.mm.UD1.Funciones.ProcesoJava;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CalculaMayor {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger(CalculaMayor.class.getName());
        Scanner teclado = new Scanner(System.in);

        System.out.print("Indica un número: ");
        String n1 = teclado.next();
        System.out.print("Indica un número: ");
        String n2 = teclado.next();
        System.out.print("Indica un número: ");
        String n3 = teclado.next();

        ProcesoJava procesoJava = new ProcesoJava();

        try {
            Process p = procesoJava.exec(MayorDeTres.class, new String[]{n1, n2, n3});
            System.out.println(procesoJava.getSalidaProceso(p));
            System.out.println("Proceso padre CalculaMayor finalizado.");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error: " + e.getMessage());
        }
    }
}
