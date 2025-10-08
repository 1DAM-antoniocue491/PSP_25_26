package org.mm.UD1.Entregable_24_25.Ejercicio3;

import org.mm.UD1.Entregable_24_25.Ejercicio2.FileSizeCalculator;
import org.mm.UD1.Funciones.processBuilder;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileOpener {
    private static final Logger logger = Logger.getLogger(FileOpener.class.getName());
    private static String notepad = "notepad.exe";
    private static String adobe = "C:\\Users\\2DAM-acuelop369\\AppData\\Local\\Google\\Chrome\\Application\\chrome.exe";
    private static String photos = "mspaint.exe";

    public static void main(String[] args) {
        if (args.length == 0) {
            logger.log(Level.SEVERE, "No has añadido ningún argumento.");
            System.exit(1);
        }

        for (String arg : args) {
            if (arg.endsWith(".txt"))
                abrirArchivo(notepad, arg);
            else if (arg.endsWith(".pdf"))
                abrirArchivo(adobe, arg);
            else if (arg.endsWith(".jpg") || arg.endsWith(".png"))
                abrirArchivo(photos, arg);
            else
                logger.log(Level.SEVERE, "No se ha detectado el tipo de fichero");
        }
    }

    public static Process abrirArchivo(String rutaAplicacion, String archivo) {
        processBuilder processBuilder = new processBuilder();
        String[] commands = {rutaAplicacion, archivo};
        Process p = processBuilder.ejecutarProceso(Arrays.stream(commands).toList());

        return p;
    }
}
