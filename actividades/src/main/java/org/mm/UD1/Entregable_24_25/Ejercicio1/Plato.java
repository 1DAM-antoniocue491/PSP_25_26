package org.mm.UD1.Entregable_24_25.Ejercicio1;

public class Plato {
    public static void main(String[] args) {
        for (String arg : args) {
            switch (arg) {
                case "gazpacho" -> System.out.println("Se está preparando un gazpacho.");
                case "salmorejo" -> System.out.println("Se está preparando un salmorejo.");
                case "pescaíto frito" -> System.out.println("Se está preparando un plato de pescaíto frito.");
                case "tortilla de camarones" -> System.out.println("Se está preparando una tortilla de camarones.");
            }
        }


    }
}
