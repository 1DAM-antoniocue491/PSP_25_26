package org.mm.UD1.Funciones;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class processBuilder {
    private static final Logger logger = Logger.getLogger(processBuilder.class.getName());

    public void ejecutar(List<String> commands)  {
        ProcessBuilder pb;
        try {
            pb = new ProcessBuilder(commands);
            pb.start();
            System.out.println("Comando ejecutado con processbuilder");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al ejecutar el comando.", e);
        }
    }

    public String ejecutarYresultado(List<String> commands)  {
        ProcessBuilder pb;
        Process p = null;
        String result = "";
        try {
            pb = new ProcessBuilder(commands);
            p = pb.start();
            p.waitFor();
            result = mostrarResultado(p);
            mostrarErrores(p);
            System.out.println("Comando ejecutado con processbuilder");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al ejecutar el comando.", e);
        }
    }

    private static String mostrarResultado(Process p) {
        InputStream is = p.getInputStream();
        int letra = 0;
        String result = "";
        try {
            while ((letra = is.read()) != -1) {
                System.out.print((char) letra);
            }
        } catch (IOException ex) {
            System.out.println("Error al leer los datos del proceso");
        }
    }

    private static void mostrarErrores(Process p) {
        System.out.println("Entramos en mostrar errores");
        InputStream is = p.getErrorStream();
        int letra = 0;
        try {
            while ((letra = is.read()) != -1) {
                System.out.print((char) letra);
            }
        } catch (IOException ex) {
            System.out.println("Error al leer los datos del proceso");
        }
    }
}
