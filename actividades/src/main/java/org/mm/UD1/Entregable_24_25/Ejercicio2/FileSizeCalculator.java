package org.mm.UD1.Entregable_24_25.Ejercicio2;

import org.mm.UD1.Funciones.ProcesoJava;
import org.mm.UD1.Funciones.processBuilder;
import org.mm.UD1.Funciones.writeFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileSizeCalculator {
    private static ProcesoJava procesoJava = new ProcesoJava();
    private static final String[] OS = System.getProperty("os.name").split(" ");
    private static final Logger logger = Logger.getLogger(FileSizeCalculator.class.getName());
    private static final String fichero = "C:\\Users\\2DAM-acuelop369\\Desktop\\highschool\\2_DAM\\PSP_25_26\\actividades\\src\\main\\java\\org\\mm\\UD1\\Entregable_24_25\\Ejercicio3\\Resultado.txt";

    public static void main(String[] args) {
        processBuilder processBuilder = new processBuilder();

        if (args.length!=1) {
            logger.log(Level.SEVERE, "No has añadido ningún argumento o has añadido más de la cuenta.");
            System.exit(1);
        }

        Process resultado = null;
        try {
            if (Arrays.asList(OS).contains("Linux")) {
                String[] commands = {"bash", "du", "-b", args[0]};
                resultado = processBuilder.ejecutarProceso(Arrays.stream(commands).toList());
            } else {
                String[] commands = {"cmd", "/c", "dir", "/s", args[0]};
                resultado = processBuilder.ejecutarProceso(Arrays.stream(commands).toList());
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
        }

        guardarResultado(args[0], obtenerTamanoTotal(resultado));
    }

    private static long obtenerTamanoTotal(Process p) {
        String[] resultado = null;

        try {
            resultado = procesoJava.getSalidaProceso(p).split("\n");
        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }

        System.out.println();

        String[] bytes = new String[0];
        for (String result : resultado) {
            String line = result.toLowerCase().trim().replaceAll("\\s+", " ");
            if (line.endsWith("bytes") || line.endsWith("bytes libres")) {
                bytes = Arrays.copyOf(bytes, bytes.length+1);
                bytes[bytes.length-1] =  line;
            }
        }

        return Long.parseLong(bytes[2].split(" ")[2].replaceAll("\\.","")) + Long.parseLong(bytes[3].split(" ")[2].replaceAll("\\.",""));
    }

    private static void guardarResultado(String ruta, long tamanoTotal) {
        writeFile writeFile = new writeFile();

        File file = new File(ruta);
        if (file.isFile()) {
            writeFile.sobreEscribirContenido(new File(fichero), "El archivo " + file.getAbsolutePath() + " tiene un tamaño total de " + tamanoTotal + " bytes", true);
        } else
            writeFile.sobreEscribirContenido(new File(fichero), "El directorio " + file.getAbsolutePath() + " tiene un tamaño total de " + tamanoTotal + " bytes", true);

    }
}
