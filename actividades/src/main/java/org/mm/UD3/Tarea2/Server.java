package org.mm.UD3.Tarea2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Server {
    private static final int PORT = 5555;
    private static final int numAleatorio = (int) (Math.random() * 100) + 1;
    private static final int totalJugadas = 10;
    private static int numJugadas = 0;
    private static boolean result = false;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Servidor iniciado");
            System.out.println("Esperando a una conexión del cliente por el puerto " + PORT);
            System.out.println("Numero: " + numAleatorio);

            printSeparador();
            while (totalJugadas > numJugadas || !result){
                Socket clientSocket = serverSocket.accept();

                HiloServer hiloServer = new HiloServer(clientSocket, numAleatorio);

                hiloServer.start();
            }

            serverSocket.close();

        } catch (IOException e) {
            System.err.println("Error: " );
        }
    }

    private static void printSeparador() {
        System.out.println("===================================");
    }
}
