package org.mm.UD3.Entregable2425;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Servidor {
    private static final int PORT = 12345;
    private static List<ClientHandler> listaClientes = new ArrayList<>();
    private static ColorFicha turno = ColorFicha.ROJO;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Servidor iniciado");
            System.out.println("Esperando a una conexión del cliente por el puerto " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();

                System.out.println("Se acaba de aceptar una conexión");

                ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());

                ClientID clientID = (ClientID) in.readObject();
                System.out.println("ClientID recibido desde el cliente");
                ClientHandler clientHandler = new ClientHandler(clientSocket, clientID, turno);
                if (turno == ColorFicha.ROJO)
                    turno = ColorFicha.VERDE;
                else
                    turno = ColorFicha.ROJO;

                listaClientes.add(clientHandler);
                System.out.println(listaClientes.size());

                if (listaClientes.size() > 2) {
                    ClientHandler cliente1 = listaClientes.get(0);
                    ClientHandler cliente2 = listaClientes.get(1);
                    listaClientes.remove(0);
                    listaClientes.remove(1);
                    cliente1.setOponente(cliente2);
                    cliente2.setOponente(cliente1);
                    Partida partida = new Partida();
                    cliente1.setPartida(partida);
                    cliente2.setPartida(partida);
                    cliente1.start();
                    cliente2.start();
                }
            }
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
}
