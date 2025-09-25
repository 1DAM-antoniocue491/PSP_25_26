package org.mm.UD1.Tarea2;

import java.util.Arrays;

public class Shell {
    private static final String[] OS = System.getProperty("os.name").split(" ");
    public static void main(String[] args) {
        if (Arrays.asList(OS).contains("Linux")) {
            System.out.println("Es un sistema operativo linux");
        } else if (Arrays.asList(OS).contains("Windows")) {
            System.out.println("Es un sistema operativo Windows");
        } else {
            System.out.println("Es otro SO desconocido");
        }
    }
}
