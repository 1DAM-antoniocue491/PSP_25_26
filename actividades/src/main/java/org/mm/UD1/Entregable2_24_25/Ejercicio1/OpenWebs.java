package org.mm.UD1.Entregable2_24_25.Ejercicio1;

import org.mm.UD1.Entregable_24_25.Ejercicio1.Restaurante;
import org.mm.UD1.Funciones.processBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OpenWebs {
    public static void main(String[] args) {
        final Logger logger = Logger.getLogger(OpenWebs.class.getName());
        String navegador = "C:\\Users\\2DAM-acuelop369\\AppData\\Local\\Google\\Chrome\\Application\\chrome.exe";

        if (args.length == 0) {
            logger.log(Level.SEVERE, "Error: Se debe de añadir un parámetro como mínimo.");
        }

        for (String url : args) {
            abrirURL(navegador, url);
        }
    }

    public static Process abrirURL (String rutaNavegador, String url) {
        String[] command = {rutaNavegador, url};
        processBuilder processBuilder = new processBuilder();
        return processBuilder.ejecutarProceso(Arrays.stream(command).toList());
    }
}
