package org.mm.UD1.Tarea3;

import org.mm.UD1.Funciones.ProcesoJava;
import org.mm.UD1.Funciones.writeFile;
import org.mm.UD1.Tarea2.Shell;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LanzadorSumador {
    private static final Logger logger = Logger.getLogger(LanzadorSumador.class.getName());
    private static String file = "C:\\Users\\2DAM-acuelop369\\Desktop\\highschool\\2_DAM\\PSP_25_26\\actividades\\src\\main\\java\\org\\mm\\UD1\\Tarea3\\salidaHijo2.txt";

    public static void main(String[] args) {
        ProcesoJava procesoJava = new ProcesoJava();

        try {
            Process p1 = procesoJava.exec(Sumador.class, new String[]{"3", "4"});
            Process p2 = procesoJava.exec(Sumador.class, new String[]{"5", "6"});

            String resultadoP1 = procesoJava.getSalidaProceso(p1);
            String resultadoP2 = procesoJava.getSalidaProceso(p2);

            System.out.println(resultadoP1);

            writeFile writeFile = new writeFile();
            writeFile.escribirContenido(new File(file), resultadoP2);
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }
}
