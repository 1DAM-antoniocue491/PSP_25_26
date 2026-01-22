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
    private static ObjectOutputStream out;
    private static ObjectInputStream in;

    public static void main(String[] args) {

        try {
            Socket serverSocket = new Socket(IP, PORT);
            System.out.println("Conexión con el servidor establecida");
            out = new ObjectOutputStream(serverSocket.getOutputStream());
            in = new ObjectInputStream(serverSocket.getInputStream());

            System.out.print("Indica tu nombre de usuario: ");
            String nombreUsuario = teclado.next();
            System.out.print("Indica el nombre de tu destinatario: ");
            String destinatario = teclado.next();
            Usuario usuario = new Usuario(nombreUsuario, destinatario);

            out.writeObject(usuario);
            System.out.println("Usuario enviado");

            while (true) {
                int opcion = printMenu();
                switch (opcion) {
                    case 1 -> {
                        sendMessage();
                        break;
                    }
                    case 2 -> {
                        viewMessage();
                        break;
                    }
                    case 3 -> {
                        changeDestinatario();
                        break;
                    }
                    case 4 -> {
                        exit();
                        break;
                    }
                }

                try {
                    Respuesta respuesta = (Respuesta) in.readObject();
                    if (respuesta.getTipo() == TipoRespuesta.NOTIFICACION) {
                        System.out.println("NOTIFICACIÓN:");
                        System.out.println(respuesta.getNotificacion());
                    } else {
                        System.out.println("LISTA DE MENSAJES:");
                        for (Mensaje mensaje : respuesta.getMensajes()) {
                            System.out.println("---------------------");
                            System.out.println(mensaje.getRemitente());
                            System.out.println(mensaje.getFechaHora());
                            System.out.println(mensaje.getContenido());
                        }
                    }
                } catch (ClassNotFoundException e) {
                    System.err.println("ClassNotFoundException: " + e.getMessage());
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

    private static void sendMessage() {
        System.out.print("Escribe un mensaje: ");
        String mensaje = teclado.next();

        try {
            Comando comando = new Comando(TipoComando.SEND, mensaje);
            out.writeObject(comando);
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
        }
    }

    private static void viewMessage() {
        try {
            Comando comando = new Comando(TipoComando.VIEW);
            out.writeObject(comando);
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
        }
    }

    private static void changeDestinatario() {
        System.out.print("Indica el nombre del destinatario preferido: ");
        String destinatario = teclado.next();

        try {
            Comando comando = new Comando(TipoComando.CHANGE, destinatario);
            out.writeObject(comando);
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
        }
    }

    private static void exit() {
        try {
            Comando comando = new Comando(TipoComando.EXIT);
            out.writeObject(comando);
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
        }
    }
}
