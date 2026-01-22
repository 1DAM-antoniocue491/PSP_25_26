package org.mm.UD3.Examen_24_25;

import org.mm.UD3.Tarea2.HiloServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Servidor {
    private static final int PORT = 6969;
    private static ObjectOutputStream out;
    private static ObjectInputStream in;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Servidor iniciado");
            System.out.println("Esperando a una conexión del cliente por el puerto " + PORT);
            GestorMensajes gestorMensajes = new GestorMensajes();

            while (true){
                Socket clientSocket = serverSocket.accept();
                System.out.println("Un cliente se acaba de conectar al servidor");
                out = new ObjectOutputStream(clientSocket.getOutputStream());
                in = new ObjectInputStream(clientSocket.getInputStream());

                System.out.println("Conexiones establecidas");
                Usuario usuario = (Usuario) in.readObject();
                System.out.println("Usuario recibido");

                ClienteHandler clienteHandler = new ClienteHandler(usuario, gestorMensajes, clientSocket);

                clienteHandler.start();
                out.close();
                in.close();
            }

        } catch (IOException e) {
            System.err.println("Error: " );
        } catch (RuntimeException e) {
            System.err.println("RuntimeException: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
}
