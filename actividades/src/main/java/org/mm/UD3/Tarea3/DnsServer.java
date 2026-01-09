package org.mm.UD3.Tarea3;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class DnsServer {
    private static final int PORT = 5555;
    private static int contador = 0;

    public static void main(String[] args) {
        Buffer buffer = new Buffer();

        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Servidor iniciado por el puerto " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();

                ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());

                PrintWriter outputServer = new PrintWriter(clientSocket.getOutputStream(), true);

                Token token = (Token) in.readObject();

                if (token.getClientCode() == 1111111111L) {
                    AdminClient adminClient = new AdminClient(buffer, token.getOperacion(), clientSocket);
                    adminClient.start();
                } else if (token.getClientCode() == 9999999999L) {
                    UserClient userClient = new UserClient(buffer, token.getOperacion(), clientSocket, out);
                    userClient.start();
                } else {
                    contador++;
                    outputServer.println("Atención!! Usted no es un cliente autorizado para el uso del servidor");
                }

                if (contador>2) {
                    System.out.println("ATTACK DETECTED!!!");
                    serverSocket.close();
                }
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getLocalizedMessage());
        } catch (Exception e) {
            System.err.println("Error: " + e.getLocalizedMessage());
        }
    }
}
