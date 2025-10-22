package org.mm.UD1.Entregable_25_26.ejer2;

import org.mm.UD1.Funciones.processBuilder;
import org.mm.UD1.Funciones.writeFile;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;


public class ProcessAnalyzer {
    private static final String[] OS = System.getProperty("os.name").split(" ");

    public static void main(String[] args) {
        writeFile writeFile = new writeFile();
        String errorFile = "C:\\Users\\2DAM-acuelop369\\Desktop\\highschool\\2_DAM\\PSP_25_26\\actividades\\src\\main\\java\\org\\mm\\UD1\\Entregable_25_26\\ejer2\\errores.txt";
        String processFile = "C:\\Users\\2DAM-acuelop369\\Desktop\\highschool\\2_DAM\\PSP_25_26\\actividades\\src\\main\\java\\org\\mm\\UD1\\Entregable_25_26\\ejer2\\procesos.txt";
        String[] commands = new String[0];
        processBuilder processBuilder = new processBuilder();
        boolean parse = false;
        ArrayList<String> process = new ArrayList<>();
        ArrayList<String> processName = new ArrayList<>();

        if (args.length != 1) {
            writeFile.sobreEscribirContenido(new File(errorFile), "No se le están pasando correctamente los argumentos", true);
            System.exit(1);
        }

        if (Arrays.stream(OS).toList().contains("Linux")) {
            commands = Arrays.copyOf(commands, commands.length+3);
            commands[0] = "bash";
            commands[1] = "ps";
            commands[2] = "-e";
        } else {
            commands = Arrays.copyOf(commands, commands.length+3);
            commands[0] = "cmd";
            commands[1] = "/C";
            commands[2] = "taskist";
        }

        String result = processBuilder.bigProcessExec(Arrays.stream(commands).toList(), errorFile);

        String[] lines = result.split("\n");

        for (String line : lines) {
            if (line.startsWith("=")) {
                parse = true;
            }
            if (parse) {
                process.add(line);
            }
        }

        for (String proceso : process) {
            proceso = proceso.replaceAll("\\s+", " ");
            String[] procesoArray = proceso.split(" ");
            System.out.println(procesoArray[0]);
            try {
                if (procesoArray[0].length() > Integer.parseInt(args[0])) {
                    processName.add(procesoArray[0]);
                }
            } catch (NumberFormatException e) {
                writeFile.sobreEscribirContenido(new File(errorFile), e.getMessage(), true);
                System.exit(1);
            }
        }

        String nombresDeProcesos = "";

        for (String name : processName) {
            nombresDeProcesos += name + "\n";
        }

        writeFile.escribirContenido(new File(processFile), nombresDeProcesos);
    }
}
