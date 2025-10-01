package org.mm.UD1.Tarea2;

import org.mm.UD1.Funciones.processBuilder;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Shell {
    private static final String[] OS = System.getProperty("os.name").split(" ");
    private static final Logger logger = Logger.getLogger(Shell.class.getName());

    public static void main(String[] args) {
        processBuilder processBuilder = new processBuilder();

        if (args.length != 1) {
            logger.log(Level.SEVERE, "No has añadido ningún argumento o has añadido más argumentos de los permitidos.");
        }

        File file = new File(args[0]);
        if (file.exists() && file.isFile()) {
            String[] commands = {"cmd", "/C", "more", args[0]};
            processBuilder.ejecutarYresultado(Arrays.stream(commands).toList());
        } else if (file.exists() && file.isDirectory()) {
            if (Arrays.asList(OS).contains("Linux")) {
                String[] commands = {"bash", "ls", args[0]};
                processBuilder.ejecutarYresultado(Arrays.stream(commands).toList());
            } else {
                String[] commands = {"cmd", "/c", "dir", args[0]};
                processBuilder.ejecutarYresultado(Arrays.stream(commands).toList());
            }
        } else {
            logger.log(Level.SEVERE, "El atributo indicado no existe.");
        }
    }
}
