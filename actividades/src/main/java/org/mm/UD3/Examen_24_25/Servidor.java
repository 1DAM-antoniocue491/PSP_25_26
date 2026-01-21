package org.mm.UD3.Examen_24_25;

import org.mm.UD3.Tarea2.HiloServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Servidor {
    private static final int PORT = 6969;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Servidor iniciado");
            System.out.println("Esperando a una conexión del cliente por el puerto " + PORT);
            GestorMensajes gestorMensajes = new GestorMensajes();

            while (true){
                Socket clientSocket = serverSocket.accept();

//                Usuario usuario;
//                List<Mensaje> mensajes;
//
//                ClienteHandler clienteHandler = new ClienteHandler(usuario, mensajes, gestorMensajes);
//
//                clienteHandler.start();
            }

//            serverSocket.close();

        } catch (IOException e) {
            System.err.println("Error: " );
        }
    }
}
