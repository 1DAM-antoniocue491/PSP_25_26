package org.mm.UD3.Tarea3;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class DnsServer {
    private static final int PORT = 5555;

    public static void main(String[] args) {
        Buffer buffer = new Buffer();
        buffer.registrar("domi");

        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Servidor iniciado por el puerto " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();

                ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
                Token token = (Token) in.readObject();

                if (token.getClientCode() == 1111111111L) {
                    // AdminClient
                } else if (token.getClientCode() == 9999999999L) {
                    // UserClient
                } else {
                    // SpywareClient
                }
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getLocalizedMessage());
        } catch (Exception e) {
            System.err.println("Error: " + e.getLocalizedMessage());
        }
    }
}
