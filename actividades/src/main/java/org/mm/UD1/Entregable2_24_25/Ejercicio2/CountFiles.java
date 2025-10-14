package org.mm.UD1.Entregable2_24_25.Ejercicio2;

import org.mm.UD1.Entregable_24_25.Ejercicio1.Restaurante;
import org.mm.UD1.Funciones.processBuilder;
import org.mm.UD1.Funciones.writeFile;

import java.io.File;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CountFiles {
    private static processBuilder processBuilder = new processBuilder();
    private static final Logger logger = Logger.getLogger(CountFiles.class.getName());

    public static void main(String[] args) {
        if (args.length == 0) {
            logger.log(Level.SEVERE, "Error: Se debe de añadir un parámetro como mínimo.");
        }

        String[] command = {"ls", args[0]};
        Process p = processBuilder.ejecutarProceso(Arrays.stream(command).toList());

        guardarResultado(args[0], obtenerNumeroFicheros(p));
    }

    private static int obtenerNumeroFicheros(Process p) {
        String resultado = org.mm.UD1.Funciones.processBuilder.mostrarResultado(p);
        String[] prueba = resultado.split("\n");

        return prueba.length;
    }

    private static void guardarResultado(String nomDir, int numArchivos) {
        writeFile writeFile = new writeFile();
        String contenido = "El directorio (" + nomDir + ") tiene " + numArchivos + " archivos.";
        writeFile.sobreEscribirContenido(new File("/home/antonio/Desktop/highschool/2_DAM/PSP_25_26/actividades/src/main/java/org/mm/UD1/Entregable2_24_25/Ejercicio2/Resultado.txt"), contenido, true);
    }
}
