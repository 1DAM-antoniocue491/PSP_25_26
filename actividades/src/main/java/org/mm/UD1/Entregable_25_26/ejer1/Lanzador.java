package org.mm.UD1.Entregable_25_26.ejer1;

import org.mm.UD1.Funciones.processBuilder;
import org.mm.UD1.Funciones.writeFile;
import org.mm.UD1.Tarea3.LanzadorSumador;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Lanzador {
    private static String editorTexto = "notepad.exe";
    private static String exploradorFicheros = "explorer.exe";
    private static processBuilder processBuilder = new processBuilder();
    private static final Logger logger = Logger.getLogger(LanzadorSumador.class.getName());
    private static String log = "C:\\Users\\2DAM-acuelop369\\Desktop\\highschool\\2_DAM\\PSP_25_26\\actividades\\src\\main\\java\\org\\mm\\UD1\\Entregable_25_26\\ejer1\\errores.log";
    private static writeFile writeFile = new writeFile();

    public static Process abrirEditorTexto (String rutaFichero) {
        File f = new File(rutaFichero);

        try {
            if (!f.exists()) {
                f.createNewFile();
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }

        String[] commands = new String[] {editorTexto, rutaFichero};
        Process process = processBuilder.ejecutarProceso(Arrays.stream(commands).toList());

        return process;
    }

    public static Process abrirExplorador (String rutaDirectorio) {
        File f = new File(rutaDirectorio);
        Process process = null;

        if (!f.exists()) {
            String error = "El fichero " + f.getName() + " no existe";
            writeFile.sobreEscribirContenido(new File(log), error, true);
        } else {
            String[] commands = new String[] {exploradorFicheros, rutaDirectorio};
            process = processBuilder.ejecutarProceso(Arrays.stream(commands).toList());
        }

        return process;
    }
}
