package org.mm.UD1.Entregable_25_26.ejer1;

import java.util.Scanner;

public class TestLanzador {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        System.out.print("Indica la ruta de un fichero: ");
        String file = teclado.next();
        System.out.println();
        System.out.print("Indica la ruta de un directorio: ");
        String dir = teclado.next();

        Lanzador.abrirEditorTexto(file);
        Lanzador.abrirExplorador(dir);
    }
}
