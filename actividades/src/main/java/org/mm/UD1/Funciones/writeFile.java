package org.mm.UD1.Funciones;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class writeFile {
    public void escribirContenido(File f, String contenido) {
        try {
            // Existencia y tratamiento del fichero
            if (!f.exists()) {
                FileWriter fichero = new FileWriter(f);

                // Escribimos el string pasado como parámetro
                fichero.write(contenido);

                // Cierre del fichero
                fichero.close();

                System.out.println("El fichero " + f.getName() + " ha sido creado y escrito correctamente");
            } else {
                f.delete();

                FileWriter fichero = new FileWriter(f);

                // Escribimos el string pasado como parámetro
                fichero.write(contenido);

                // Cierre del fichero
                fichero.close();

                System.out.println("El fichero " + f.getName() + " ha sido sobrescrito correctamente");
            }
        } catch (IOException e) {
            System.err.println("Error al generar al fichero");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void sobreEscribirContenido(File f, String contenido, boolean sobreescribir) {
        try {
            // Existencia y tratamiento del fichero
            if (!f.exists()) {
                f.createNewFile();
            } else
                contenido = "\r\n" + contenido;

            FileWriter fichero = new FileWriter(f, sobreescribir);
            BufferedWriter bw = new BufferedWriter(fichero);
            bw.write(contenido);
            bw.close();
        } catch (IOException e) {
            System.err.println("Error al generar al fichero");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
