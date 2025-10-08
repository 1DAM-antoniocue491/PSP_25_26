package org.mm.UD1.Entregable_24_25.Ejercicio1;

import org.mm.UD1.Funciones.ProcesoJava;
import org.mm.UD1.Funciones.writeFile;
import org.mm.UD1.Tarea3.LanzadorSumador;
import org.mm.UD1.Tarea3.Sumador;

import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Restaurante {
    private static final Logger logger = Logger.getLogger(Restaurante.class.getName());

    public static void main(String[] args) {
        ProcesoJava procesoJava = new ProcesoJava();
        writeFile writeFile = new writeFile();

        ArrayList<String> platos = new ArrayList<>();
        platos.add("gazpacho");
        platos.add("salmorejo");
        platos.add("pescaíto frito");
        platos.add("tortilla de camarones");


        try {
            Process p1 = procesoJava.exec(Plato.class, new String[]{platos.get((int) (Math.random() * platos.size()))});

            String resultadoP1 = procesoJava.getSalidaProceso(p1);

            if (args.length > 1 && args[0].equals("-f")) {
                writeFile.sobreEscribirContenido(new File(args[1]), LocalTime.now() + " -> " + resultadoP1, true);
            } else {
                System.out.println(LocalTime.now() + " -> " + resultadoP1);
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }
}
