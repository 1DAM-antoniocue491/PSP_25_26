package org.mm.UD1.Entregable_25_26.ejer3;

public class Operador {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("Error. Parámetros mal indicados");
        }

        int result = switch (args[1]) {
            case "'+'" -> Integer.parseInt(args[0]) + Integer.parseInt(args[2]);
            case "'-'" -> Integer.parseInt(args[0]) - Integer.parseInt(args[2]);
            case "'/'" -> Integer.parseInt(args[0]) / Integer.parseInt(args[2]);
            case "'*'" -> Integer.parseInt(args[0]) * Integer.parseInt(args[2]);
            default -> 0;
        };

        System.out.println(result);
    }
}
