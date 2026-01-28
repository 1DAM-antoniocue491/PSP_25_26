package org.mm.UD3.Recuperación_HundirLaFlota;

import org.mm.UD3.Tarea2.HiloServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorHundirLaFlota {
    private static final int PORT = 8282;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Servidor iniciado");
            System.out.println("Esperando a una conexión del cliente por el puerto " + PORT);

            while (true){
                Socket clientSocket = serverSocket.accept();


            }

            // serverSocket.close();

        } catch (IOException e) {
            System.err.println("Error: " );
        }
    }
}
