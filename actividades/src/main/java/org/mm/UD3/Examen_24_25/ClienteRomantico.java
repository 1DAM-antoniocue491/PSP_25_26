package org.mm.UD3.Examen_24_25;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClienteRomantico {
    private final static String IP = "172.22.160.5";
    private final static int PORT = 6969;
    private static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {

        try {
            Socket serverSocket = new Socket(IP, PORT);
            ObjectOutputStream out = new ObjectOutputStream(serverSocket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(serverSocket.getInputStream());

            System.out.print("Indica tu nombre de usuario: ");
            String nombreUsuario = teclado.next();
            System.out.print("Indica el nombre de tu destinatario: ");
            String destinatario = teclado.next();
            Usuario usuario = new Usuario(nombreUsuario, destinatario);

            out.writeObject(usuario);

            while (true) {
                int opcion = printMenu();
                switch (opcion) {
                    case 1 -> {

                    }
                    case 2 -> {

                    }
                    case 3 -> {

                    }
                    case 4 -> {

                    }
                }
            }
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
        }
    }

    private static int printMenu() {
        System.out.println("------------");
        System.out.println("  1. Enviar mensaje");
        System.out.println("  2. Ver respuestas recibidas");
        System.out.println("  3. Cambiar destinatario");
        System.out.println("  4. Salir");
        System.out.print("Elige una opción: ");
        int opcion = teclado.nextInt();

        if (opcion > 0 && opcion < 5) {
            return opcion;
        } else {
            return printMenu();
        }
    }
}
