package org.mm.UD1.Funciones;

import java.io.*;
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
        return result;
    }

    public String bigProcessExec(List<String> commands, String logError)  {
        Process p = null;
        StringBuilder result = new StringBuilder();
        try {
            ProcessBuilder pb = new ProcessBuilder(commands);
            p = pb.start();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader((p.getInputStream())))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line + "\n");
                }
            }

            File f = new File(logError);

            if (logError.isEmpty() || !f.isFile()) {
                mostrarErrores(p);
            } else {
                mostrarErrores(p, logError);
            }

            p.waitFor();
            System.out.println("Comando ejecutado con processbuilder");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al ejecutar el comando.", e);
        }
        return result.toString();
    }

    public Process ejecutarProceso(List<String> commands)  {
        Process p = null;
        try {
            p = new ProcessBuilder(commands).start();
            p.waitFor();
            mostrarErrores(p);
            System.out.println("Comando ejecutado con processbuilder");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al ejecutar el comando.", e);
        }
        return p;
    }

    public String mostrarResultado(Process p) {
        InputStream is = p.getInputStream();
        int letra = 0;
        String result = "";
        try {
            while ((letra = is.read()) != -1) {
                result += (char) letra;
            }
        } catch (IOException ex) {
            System.out.println("Error al leer los datos del proceso");
        }
        return result;
    }

    public void mostrarErrores(Process p) {
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

    public void mostrarErrores(Process p, String logError) {
        writeFile writeFile = new writeFile();
        System.out.println("Entramos en mostrar errores");
        InputStream is = p.getErrorStream();
        String error = "";
        int letra = 0;
        try {
            while ((letra = is.read()) != -1) {
                error += (char) letra;
            }
        } catch (IOException ex) {
            System.out.println("Error al leer los datos del proceso");
        }
        writeFile.sobreEscribirContenido(new File(logError), error, true);
    }
}
