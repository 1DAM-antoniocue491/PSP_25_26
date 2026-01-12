package org.mm.UD3.Entregable2425;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    private static final int PORT = 12345;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Servidor iniciado");
            System.out.println("Esperando a una conexión del cliente por el puerto " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
            }
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
        }
    }
}
